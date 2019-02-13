package com.qql.lifting.mvp.module.entity;

import com.qql.lifting.interfaces.IBannerBean;

public class BannerBean implements IBannerBean {
    private String imgUrl;
    private int productId;
    private String title;

    public BannerBean(int productId,String imgUrl) {
        this.imgUrl = imgUrl;
        this.productId = productId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return productId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
