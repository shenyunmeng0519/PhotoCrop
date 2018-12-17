package com.xingen.photocroplib.internal.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

public class UriUtils {

    /**
     * 生成File文件路径对应的Uri
     *
     * 7.0 及其以上使用FileProvider替换'file://'访问
     * @param context
     * @param filePath
     * @return
     */
    public static Uri getUriFromFile(Context context,String filePath){

        if (Build.VERSION.SDK_INT>=24){
           return FileProvider.getUriForFile(context, queryApplicationId(context) + ".provider", new File(filePath));
        }else{
            return  Uri.fromFile(new File(filePath));
        }
    }
    /**
     * 查询BuildConfig类中的APPLICATION_ID
     *
     * @param context
     * @return
     */
  private static String queryApplicationId(Context context) {
        String applicationId;
        try {
            String packageName = context.getPackageName();
            Class<?> BuildConfigClass = Class.forName(packageName + ".BuildConfig");
            Field APPLICATION_ID_Filed = BuildConfigClass.getDeclaredField("APPLICATION_ID");
            APPLICATION_ID_Filed.setAccessible(true);
            applicationId = (String) APPLICATION_ID_Filed.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            applicationId = context.getPackageName();
        }
        return applicationId;
    }
}
