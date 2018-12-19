package com.xingen.photocroplib.internal.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author HeXinGen
 * date 2018/12/19.
 */
public class FileUtils {
    public static final String bitmapFormat = ".png";
    /**
     * 创建图片的绝对路径
     * getExternalFilesDir()提供的是私有的目录，在app卸载后会被删除
     *
     * @param context
     * @param
     * @return
     */
    public static String createPhotoPath(Context context) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        } else {
            cachePath =context.getFilesDir().getAbsolutePath();
        }
        return new File(cachePath +File.separator+ createPhotoName()).getAbsolutePath();
    }
    /**
     * 生成bitmap的文件名:日期，md5加密
     *
     * @return
     */
    public static String createPhotoName() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            String currentDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            mDigest.update(currentDate.getBytes("utf-8"));
            byte[] b = mDigest.digest();
            for (int i = 0; i < b.length; ++i) {
                String hex = Integer.toHexString(0xFF & b[i]);
                if (hex.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName = stringBuilder.toString() + bitmapFormat;
        return fileName;
    }


}
