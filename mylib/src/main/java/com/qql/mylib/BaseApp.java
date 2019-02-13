package com.qql.mylib;

import android.app.Application;

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

    private void initUm() {
    }

    public void toLogin(){

    }
}
