package com.xingen.photocroplib;

import android.graphics.Bitmap;

import com.xingen.photocroplib.internal.listener.BitmapListener;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

public class PhotoTask {

    private  int requestCode;
    private  Type type;

    private String filePath;



    public Type getType() {
        return type;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
