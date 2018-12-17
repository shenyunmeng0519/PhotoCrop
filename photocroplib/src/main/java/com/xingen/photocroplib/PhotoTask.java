package com.xingen.photocroplib;

import android.graphics.Bitmap;

import com.xingen.photocroplib.internal.listener.BitmapListener;
import com.xingen.photocroplib.internal.utils.MathUtils;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

public class PhotoTask {

    private final int requestCode;
    private Type type;

    private String filePath;
    private WeakReference<Object> weakReference;

    private boolean success;

    public PhotoTask() {
        requestCode = Integer.valueOf(MathUtils.getRandom(6));
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public Type getType() {
        return type;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public WeakReference<Object> getWeakReference() {
        return weakReference;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * 任务类型，拍照，图库，裁剪,加载bitmap
     */
    public enum Type {
        camera,
        gallery,
        crop,
        bitmap;
    }


}
