package com.qql.lifting.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.qql.lifting.Constants;
import com.qql.lifting.R;
import com.qql.lifting.notification.NotificationConfig;
import com.qql.lifting.notification.NotificationType;
import com.qql.lifting.notification.NotificationUtil;
import com.qql.lifting.utils.LogUtil;

public class RemindBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("xxxxxxxxx");
        NotificationConfig config = new NotificationConfig.Builder().setType(NotificationType.SIMPLE)
                .setSmallIcon(R.mipmap.ic_launcher).setTitle("notification")
                .setContext(context).setBody("xxxxx").setTargetType("1")
                .setTargetString(intent.getStringExtra(Constants.KEY_APP_PKG))
                .build();
        NotificationUtil.notification(config,"");
    }
}
