package com.xingen.photocroplib.internal.execute;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.os.Build;
import android.support.v4.app.Fragment;


import com.xingen.photocroplib.internal.utils.PermissionUtils;

/**
 * @author HeXinGen
 * date 2018/12/17.
 */
 class PermissionHelper {


    /**
     * 检查是否获取到读写权限
     * @param context
     * @return
     */
   public boolean checkWritePermission(Context context){
       return PermissionUtils.hasPermission(context,Permission.PERMS_WRITE);
   }
   public void requestWritePermission(Object context,int requestCode){
       if (context instanceof Activity && Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
           Activity activity = (Activity) context;
           activity.requestPermissions(Permission.PERMS_WRITE,requestCode);
       }else if (context instanceof Fragment  && Build.VERSION.SDK_INT > Build.VERSION_CODES.M ){
           Fragment fragment=(Fragment) context;
           fragment.requestPermissions(Permission.PERMS_WRITE,requestCode);
       }
   }
   public  final static class  Permission{
        /**
         * 写入权限的请求code,提示语，和权限码
         */
        public final  static String[] PERMS_WRITE ={Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }
}
