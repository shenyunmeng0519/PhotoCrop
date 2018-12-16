package com.xingen.photocroplib.internal.constants;

import android.Manifest;

/**
 * Created by ${HeXinGen} on 2018/12/16.
 * blog博客:http://blog.csdn.net/hexingen
 */

public final class Constants {
    /**
     * 写入权限的请求code,提示语，和权限码
     */
    public final static  int WRITE_PERMISSION_CODE=1111111111;
    public final static  String WRITE_PERMISSION_TIP ="为了正常使用，请允许读写权限!";
    public final  static String[] PERMS_WRITE ={Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /**
     * 相机，图库的请求code
     */
    public final static int picture_request_code =1111111112;
    public final static int gallery_request_code =1111111113;
}
