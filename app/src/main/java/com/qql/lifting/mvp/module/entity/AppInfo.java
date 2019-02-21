package com.qql.lifting.mvp.module.entity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.qql.lifting.utils.ApkUtil;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity
public class AppInfo implements Serializable {
    private static final long serialVersionUID = 2690655667575581746L;
    @Id(autoincrement = true)
    private Long id;
    private String pkg; //包名
    @Transient
    private Drawable ico;       //图标
    private String label;        //应用标签
    @Transient
    private Intent intent;     //启动应用程序的Intent ，一般是Action为Main和Category为Lancher的Activity

    @Generated(hash = 925323597)
    public AppInfo(Long id, String pkg, String label) {
        this.id = id;
        this.pkg = pkg;
        this.label = label;
    }

    @Generated(hash = 1656151854)
    public AppInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Intent getIntent(Context context) {
        if (intent == null){
            intent = ApkUtil.getIntentByPkg(context,pkg);
        }
        return intent;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public Drawable getIco(Context context) {
        if (ico == null){
            ico = ApkUtil.getIconByPkg(context,pkg);
        }
        return ico;
    }

    public void setIco(Drawable ico) {
        this.ico = ico;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

