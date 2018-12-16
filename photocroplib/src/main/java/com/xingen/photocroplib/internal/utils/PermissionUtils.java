package com.xingen.photocroplib.internal.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

 class PermissionUtils {
    /**
     * 检查申请到权限
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hansPermission(Context context,String ...permissions){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        for (String permission:permissions){
            if (ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }











}
