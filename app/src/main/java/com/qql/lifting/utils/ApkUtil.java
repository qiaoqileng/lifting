package com.qql.lifting.utils;

import android.content.Context;
import android.content.pm.PackageInfo;


/**
 * author xupj
 * e-mail xupingjie@hztianque.com
 * time   2018/9/14
 * desc   Apk 基本参数获取
 */
public class ApkUtil {

   /**
    * 获取宿主的app版本号
    * @param context   context对象不管是宿主的还是插件的context 都获取的是宿主的版本号
    * @return 版本号
    */
   public static long getVersionCode(Context context){
      try {
         PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
         //noinspection deprecation
         return packageInfo.versionCode;
      }catch (Exception e){
         return 0;
      }
   }

   /**
    * 获取宿主的app版本名称
    * @param context   context对象不管是宿主的还是插件的context 都获取的是宿主的版本名称
    * @return 版本号
    */
   public static String getVersionName(Context context){
      try {
         PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
         return packageInfo.versionName;
      }catch (Exception e){
         return "";
      }
   }
}
