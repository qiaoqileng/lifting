package com.qql.lifting.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.qql.lifting.mvp.module.entity.AppInfo;

import java.util.ArrayList;
import java.util.List;


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

   public static List<AppInfo> GetAppList(Context context){
      List<AppInfo> list=new ArrayList<>();
      PackageManager pm = context.getPackageManager();
      Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
      mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
      List<ResolveInfo> activities   = pm.queryIntentActivities(mainIntent, 0);
      for(ResolveInfo info : activities){
         String packName = info.activityInfo.packageName;
         if(packName.equals(context.getPackageName())){
            continue;
         }
         AppInfo mInfo = new AppInfo();
         mInfo.setIco(info.activityInfo.applicationInfo.loadIcon(pm));
         mInfo.setLabel(info.activityInfo.applicationInfo.loadLabel(pm).toString());
         mInfo.setPkg(packName);
         // 为应用程序的启动Activity 准备Intent
         Intent launchIntent = new Intent();
         launchIntent.setComponent(new ComponentName(packName,
                 info.activityInfo.name));
         mInfo.setIntent(launchIntent);
         list.add(mInfo);
      }
      return list;
   }

   public static Drawable getIconByPkg(Context context,String pkg) {
      PackageManager pm = context.getPackageManager();
      try {
         return pm.getApplicationIcon(pkg);
      } catch (PackageManager.NameNotFoundException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static Intent getIntentByPkg(Context context,String pkg) {
      PackageManager pm = context.getPackageManager();
      return pm.getLaunchIntentForPackage(pkg);
   }

   public static String getNameByPkg(Context context,String pkg){
      PackageManager pm = context.getPackageManager();
      try {
         return pm.getApplicationLabel(pm.getApplicationInfo(pkg,0)).toString();
      } catch (Exception e) {
         e.printStackTrace();
         return "";
      }
   }
}
