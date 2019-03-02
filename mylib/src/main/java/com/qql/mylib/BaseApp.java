package com.qql.mylib;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import java.util.ResourceBundle;


public class BaseApp extends Application {
    private static BaseApp instance;

    public static BaseApp getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initUm();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initUm() {
    }

    public void toLogin(){

    }
}
