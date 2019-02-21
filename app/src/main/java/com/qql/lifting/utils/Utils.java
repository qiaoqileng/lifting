package com.qql.lifting.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

public class Utils {
    public static boolean isEmptyList(List list){
        return list == null || list.size() == 0;
    }

    public static void jumpApp(Context context, String pkg) {
        if (context == null || TextUtils.isEmpty(pkg)){
            return;
        }
        PackageManager pm = context.getPackageManager();
        if (checkApkExist(context,pkg)){
            context.startActivity(pm.getLaunchIntentForPackage(pkg));
        }
    }

    private static boolean checkApkExist(Context context, String packageName){
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
