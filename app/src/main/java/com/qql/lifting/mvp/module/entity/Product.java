package com.qql.lifting.mvp.module.entity;

import java.util.List;

public class Product extends SimpleProduct {
    private String headImg;
    private List<String> imgs;
    private float realPrice;
    private String content;
    private float price;
    private String type;
    private Brand brand;
    private Shop shop;
    private int lookCount;
    private int collectCount;
    private boolean isCollected;
    private boolean isSendFree;
    private Dic currType;//颜色规格字典项
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Shop getShop() {
        return shop;
    }

    public int getLookCount() {
        return lookCount;
    }

    public boolean isSendFree() {
        return isSendFree;
    }

    public void setSendFree(boolean sendFree) {
        isSendFree = sendFree;
    }

    public void setLookCount(int lookCount) {
        this.lookCount = lookCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public Dic getCurrType() {
        return currType;
    }

    public void setCurrType(Dic currType) {
        this.currType = currType;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    public float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(float realPrice) {
        this.realPrice = realPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
