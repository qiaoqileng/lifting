package com.qql.lifting.mvp.module.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SimpleProduct implements ITagItem{
    @Id(autoincrement = true)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SimpleProduct() {
    }

    public SimpleProduct(String name) {
        this.name = name;
    }

    @Generated(hash = 1121374309)
    public SimpleProduct(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getTagName() {
        return name;
    }
}
