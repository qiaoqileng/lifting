package com.qql.lifting;

import android.content.Intent;

import com.qql.lifting.activity.LoginActivity;
import com.qql.lifting.config.UserConfig;
import com.qql.lifting.db.manager.GreenDaoManager;
import com.qql.mylib.BaseApp;

public class App extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        GreenDaoManager.initDatabase();
        UserConfig.initUserLoginStatus();
    }

    public void toLogin(){
        startActivity(new Intent(this, LoginActivity.class));
    }
}
