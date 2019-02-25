package com.qql.lifting.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;

import com.qql.lifting.utils.HandlerUtil;

import java.util.Iterator;
import java.util.List;

public class NotificationUtil {
    private static NotificationManager mManager;
    public static final String PRIMARY_CHANNEL = "default";
    private static volatile int notificationId = 0;

    public NotificationUtil() {
    }

    @RequiresApi(
        api = 26
    )
    private static NotificationChannel createChannel() {
        NotificationChannel chan1 = new NotificationChannel("default", "Primary Channel", 3);
        chan1.setLightColor(-16711936);
        chan1.setLockscreenVisibility(0);
        return chan1;
    }

    private static NotificationManager getManager(Context context) {
        if (mManager == null) {
            mManager = (NotificationManager)context.getSystemService("notification");
        }

        return mManager;
    }

    private static int getNotificationId() {
        ++notificationId;
        if (notificationId > 10000) {
            notificationId = 0;
        }

        return notificationId;
    }

    public static boolean isBing(String remindType) {
        return remindType != null && remindType.contains("ring");
    }

    public static boolean isShake(String remindType) {
        return remindType != null && remindType.contains("shake");
    }

    public static boolean isBln(String remindType) {
        return remindType != null && remindType.contains("bln");
    }

    public static void notification(final NotificationConfig notificationConfig, final String evenType) {
        HandlerUtil.post(() -> NotificationUtil.notificationInner(notificationConfig, evenType));
    }

    private static void notificationInner(NotificationConfig notificationConfig, String evenType) {
        Notification notification = null;
        NotificationManager manager = getManager(notificationConfig.context);
        Builder builder;
        if (VERSION.SDK_INT >= 26) {
            try {
                NotificationChannel chan1 = createChannel();
                manager.createNotificationChannel(chan1);
                builder = new Builder(notificationConfig.context, "default");
            } catch (Throwable var7) {
                builder = new Builder(notificationConfig.context);
            }
        } else {
            builder = new Builder(notificationConfig.context);
        }

        builder.setContentTitle(notificationConfig.title).setContentText(notificationConfig.body).setWhen(System.currentTimeMillis()).setSmallIcon(notificationConfig.SmallIcon).setLargeIcon(notificationConfig.LargeIcon).setAutoCancel(true).setPriority(2).setDefaults(-1);
        if (isBing(notificationConfig.remindType)) {
            Uri uri = RingtoneManager.getDefaultUri(4);
            builder.setSound(uri);
        }

        if (isShake(notificationConfig.remindType)) {
            builder.setVibrate(new long[]{0L, 1000L, 1000L, 1000L});
        }

        if (isBln(notificationConfig.remindType)) {
            builder.setLights(-16711936, 1000, 2000);
        }

        Intent intent = getIntent(notificationConfig);
        if (intent != null) {
            PendingIntent contentIntent = PendingIntent.getActivity(notificationConfig.context, 0, intent, 0);
            builder.setContentIntent(contentIntent);
        }

        switch(notificationConfig.type) {
        case SIMPLE:
            if (VERSION.SDK_INT >= 16) {
                notification = builder.build();
            } else {
                notification = builder.getNotification();
            }
            break;
        case Long_text:
            if (VERSION.SDK_INT >= 16) {
                notification = builder.setStyle((new BigTextStyle()).bigText(notificationConfig.body)).build();
            } else {
                notification = builder.setStyle((new BigTextStyle()).bigText(notificationConfig.body)).getNotification();
            }
            break;
        case Big_pic:
            BigPictureStyle style = new BigPictureStyle();
            style.setBigContentTitle(notificationConfig.title);
            style.setSummaryText(notificationConfig.body);
            if (new Integer(notificationConfig.BigIcon) != null) {
                style.bigPicture(BitmapFactory.decodeResource(notificationConfig.context.getResources(), notificationConfig.BigIcon));
            }

            if (VERSION.SDK_INT >= 16) {
                notification = builder.setStyle(style).build();
            } else {
                notification = builder.setStyle(style).getNotification();
            }
        }

        if (notification != null) {
            if (isBing(notificationConfig.remindType)) {
                notification.defaults |= 1;
            }

            if (isShake(notificationConfig.remindType)) {
                notification.defaults |= 2;
            }

            if (isBln(notificationConfig.remindType)) {
                notification.ledARGB = -16711936;
                notification.ledOnMS = 1000;
                notification.ledOffMS = 1000;
                notification.flags |= 1;
            }

            manager.notify(getNotificationId(), notification);
        }

    }

    public static Intent getIntent(NotificationConfig notificationConfig) {
        if (notificationConfig == null) {
            return null;
        } else {
            Intent mIntent = null;
            if (!TextUtils.isEmpty(notificationConfig.targetString)) {
                if (!"1".equals(notificationConfig.targetType) && !"2".equals(notificationConfig.targetType)) {
                    if ("3".equals(notificationConfig.targetType)) {
                        Uri uri = Uri.parse(notificationConfig.targetString);
                        mIntent = new Intent("android.intent.action.VIEW", uri);
                    }
                } else {
                    try {
                        if ("1".equals(notificationConfig.targetType)) {
                            mIntent = getIntent(notificationConfig.context, notificationConfig.targetString);
                        } else {
                            Class clazz = Class.forName(notificationConfig.targetString);
                            mIntent = new Intent(notificationConfig.context, clazz);
                        }

                        if (notificationConfig.targetParams != null && notificationConfig.targetParams.size() > 0) {
                            Iterator keyIterator = notificationConfig.targetParams.keySet().iterator();

                            while(keyIterator.hasNext()) {
                                String key = (String)keyIterator.next();
                                String v = (String)notificationConfig.targetParams.get(key);
                                mIntent.putExtra(key, v);
                            }
                        }
                    } catch (Throwable var5) {
                        ;
                    }
                }
            }

            return mIntent;
        }
    }

    public static Intent getIntent(Context context, String pkgName) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(pkgName, 0);
            Intent resolveIntent = new Intent("android.intent.action.MAIN", (Uri)null);
            resolveIntent.addCategory("android.intent.category.LAUNCHER");
            resolveIntent.setPackage(pi.packageName);
            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
            ResolveInfo ri = (ResolveInfo)apps.iterator().next();
            if (ri != null) {
                String packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                return intent;
            }
        } catch (Throwable var10) {
            var10.printStackTrace();
        }

        return null;
    }
}
