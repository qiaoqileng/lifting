package com.qql.lifting.mvp.module.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Remind {
    @Id(autoincrement=true)
    private Long id;
    private String title;
    private String content;
    private String pkg;
    private Date remindDate;
    private Date createDate;
    private String typeName;

    @Generated(hash = 1310433252)
    public Remind(Long id, String title, String content, String pkg,
            Date remindDate, Date createDate, String typeName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pkg = pkg;
        this.remindDate = remindDate;
        this.createDate = createDate;
        this.typeName = typeName;
    }

    @Generated(hash = 1173539496)
    public Remind() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
