package com.xingen.photocroplib.internal.execute;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.v4.app.Fragment;

import com.xingen.photocroplib.PhotoTask;
import com.xingen.photocroplib.internal.listener.BitmapListener;
import com.xingen.photocroplib.internal.utils.CameraUtils;
import com.xingen.photocroplib.internal.utils.FileBitmapUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

class TaskExecutorImpl extends TaskExecutor {
    private static final String TAG = TaskExecutorImpl.class.getSimpleName();
    private static TaskExecutorImpl instance;
    private Handler workThread;
    private Looper looper;
    private Handler mainThread;
    private List<PhotoTask> photoTaskList;
    private PermissionHelper permissionHelper;
    static {
        instance = new TaskExecutorImpl();
    }
    private TaskExecutorImpl() {
        this.permissionHelper = new PermissionHelper();
        this.photoTaskList = new CopyOnWriteArrayList<>();
        HandlerThread handlerThread = new HandlerThread(TAG, Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        looper = handlerThread.getLooper();
        workThread = new Handler(looper);
        mainThread = new Handler(Looper.getMainLooper());
    }

    public static TaskExecutorImpl getInstance() {
        return instance;
    }

    @Override
    public void execute(PhotoTask task) {
        photoTaskList.add(task);
        Activity context = getContext(task);
        if (!permissionHelper.checkWritePermission(context)) {
            permissionHelper.requestWritePermission(context, task.getRequestCode());
            return;
        }
        safeExecute(context, task);
    }

    private Activity getContext(PhotoTask task) {
        Object object = task.getWeakReference() != null ? task.getWeakReference().get() : null;
        if (object != null) {
            Activity context = null;
            if (object instanceof Activity) {
                context = (Activity) object;
            } else if (object instanceof Fragment) {
                context = ((Fragment) object).getActivity();
            }
            return context;
        } else {
            return null;
        }
    }

    private void safeExecute(Activity context, PhotoTask task) {
        switch (task.getType()) {
            case camera:
                CameraUtils.openCamera(context, task.getRequestCode(), task.getFilePath());
                break;
            case crop:

                break;
            case gallery:
                CameraUtils.openGallery(context, task.getRequestCode());
                break;
            default:
                break;
        }
    }

    @Override
    public void handlePermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        for (PhotoTask task : photoTaskList) {
            if (requestCode == task.getRequestCode()) {
                boolean result = true;
                for (int permission : grantResults) {
                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    Activity context = getContext(task);
                    if (context == null) return;
                    safeExecute(context, task);
                }
                break;
            }
        }
    }

    @Override
    public PhotoTask queryTask(int requestCode, int resultCode, Intent intent) {
        for (PhotoTask task : photoTaskList) {
            if (requestCode == task.getRequestCode()) {
                task.setSuccess(resultCode == Activity.RESULT_OK);
                if (resultCode == Activity.RESULT_OK) {
                    if (task.getType() == PhotoTask.Type.gallery) {
                        Uri uri = intent.getData();
                        task.setFilePath(CameraUtils.uriConvertPath(getContext(task), uri));
                    }
                }
                photoTaskList.remove(task);
                return task;
            }
        }
        return null;
    }

    @Override
    public Bitmap synLoadBitmap(String filePath, int targetWidth, int targetHeight) {
        return FileBitmapUtils.createBitmap(filePath, targetWidth, targetHeight);
    }

    @Override
    public void asynLoadBitmap(String filePath, int targetWidth, int targetHeight, BitmapListener bitmapListener) {
        if (bitmapListener == null) return;
        workThread.post(() -> {
            Bitmap bitmap = FileBitmapUtils.createBitmap(filePath, targetWidth, targetHeight);
            mainThread.post(() -> bitmapListener.result(filePath, bitmap));
        });
    }
}
