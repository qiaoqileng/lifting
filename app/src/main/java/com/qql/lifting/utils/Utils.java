package com.qql.lifting.utils;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.qql.lifting.App;
import com.qql.lifting.mvp.module.entity.Remind;

import java.util.List;

import static com.qql.lifting.Constants.KEY_APP_PKG;

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

    public static void startRemind(Context context, Remind remind){
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP,remind.getRemindDate().getTime(),6000,remind.getPendingIntent());
    }

    public static void removeRemind(Context context,Remind remind){
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(remind.getPendingIntent());
    }

    public static Intent getRemindBroadCast(String pkg){
        Intent intent = new Intent("action.broadcast.remind");
        intent.putExtra(KEY_APP_PKG,pkg);
        return intent;
    }
}
