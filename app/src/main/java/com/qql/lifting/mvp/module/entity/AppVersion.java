package com.qql.lifting.mvp.module.entity;

import android.content.Context;

import com.qql.lifting.utils.ApkUtil;

public class AppVersion {
    private int versionCode;
    private String versionName;
    private String url;
    private String content;
    private String tag;

    private boolean needUpdate(Context context){
        return versionCode > ApkUtil.getVersionCode(context);
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
