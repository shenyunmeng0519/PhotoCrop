package com.xingen.photocroplib.internal.execute;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

import com.xingen.photocroplib.PhotoTask;
import com.xingen.photocroplib.internal.listener.BitmapListener;
import com.xingen.photocroplib.internal.utils.FileBitmapUtils;

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
    static {
        instance = new TaskExecutorImpl();
    }
    private TaskExecutorImpl() {
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
        switch (task.getType()) {
            case crop:
                break;
            case gallery:
                break;
            case camera:
                break;
            default:
                break;
        }
    }



    @Override
    public PhotoTask queryTask(int requestCode, int resultCode, Intent intent) {
        return null;
    }
    @Override
    public Bitmap synLoadBitmap(String filePath, int targetWidth, int targetHeight) {
        return FileBitmapUtils.createBitmap(filePath,targetWidth,targetHeight);
    }
    @Override
    public void asynLoadBitmap(String filePath, int targetWidth, int targetHeight, BitmapListener bitmapListener) {
        if (bitmapListener==null) return;
        workThread.post(()->{
            Bitmap bitmap= FileBitmapUtils.createBitmap(filePath,targetWidth,targetHeight);
            mainThread.post(()->{
               bitmapListener.result(filePath,bitmap);
            });
        });
    }
}
