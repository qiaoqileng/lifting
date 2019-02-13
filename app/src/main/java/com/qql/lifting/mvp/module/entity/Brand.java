package com.qql.lifting.mvp.module.entity;

import com.qql.lifting.utils.DateFormat;

import java.util.Date;

public class Brand {
    private Long id;
    private String name;
    private String content;
    private String createDate;

    public Brand(String name, String content) {
        this.name = name;
        this.content = content;
        createDate = DateFormat.getDateAsString(new Date(),"yyyy-MM-DD");
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
