package com.qql.lifting;

import android.content.Intent;

import com.qql.lifting.activity.LoginActivity;
import com.qql.lifting.config.UserConfig;
import com.qql.lifting.db.manager.GreenDaoManager;
import com.qql.mylib.BaseApp;

import cn.jpush.im.android.api.JMessageClient;

public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        initJPush();
        GreenDaoManager.initDatabase();
        UserConfig.initUserLoginStatus();
    }

    private void initJPush() {
        JMessageClient.setDebugMode(BuildConfig.DEBUG);
        JMessageClient.init(this);
    }

    public void toLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
