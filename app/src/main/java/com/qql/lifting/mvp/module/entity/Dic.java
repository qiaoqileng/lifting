package com.qql.lifting.mvp.module.entity;

public class Dic implements ITagItem{
    private long id;
    private String displayName;
    private String preImg;

    public Dic(String displayName,String preImg) {
        this.displayName = displayName;
        this.preImg = preImg;
    }

    public Dic() {
    }

    public String getPreImg() {
        return preImg;
    }

    public void setPreImg(String preImg) {
        this.preImg = preImg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getTagName() {
        return displayName;
    }
}
