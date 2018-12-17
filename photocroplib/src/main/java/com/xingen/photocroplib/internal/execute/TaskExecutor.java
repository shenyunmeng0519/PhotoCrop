package com.xingen.photocroplib.internal.execute;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.xingen.photocroplib.PhotoTask;
import com.xingen.photocroplib.internal.listener.BitmapListener;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

public abstract class TaskExecutor {

    public static TaskExecutor getInstance(){
        return TaskExecutorImpl.getInstance();
    }

    /**
     * 执行任务
     * @param task
     */
    public  abstract  void execute(PhotoTask task);

    /**
     * 查询任务
     * @param requestCode
     * @param resultCode
     * @param intent
     * @return
     */
    public abstract  PhotoTask queryTask(int requestCode, int resultCode, Intent intent);

    /**
     * 处理权限结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public abstract  void handlePermissionResult(int requestCode, String[] permissions, int[] grantResults);


    /**
     * 同步加载
     * @param filePath
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public  abstract Bitmap synLoadBitmap(String filePath, int targetWidth, int targetHeight);

    /**
     *  异步加载，主线程回调
     * @param filePath
     * @param targetWidth
     * @param targetHeight
     * @param bitmapListener
     */
    public  abstract void   asynLoadBitmap(String filePath, int targetWidth, int targetHeight, BitmapListener bitmapListener);

}
