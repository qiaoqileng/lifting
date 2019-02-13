package com.qql.lifting.mvp.base;

import com.qql.lifting.utils.Utils;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private int total;
    private int page;
    private int pageSize;
    private List<T> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Page() {
    }

    public Page(List<T> data) {
        this.data = data;
        if (!Utils.isEmptyList(data)){
            total = data.size();
            pageSize = data.size();
            page = 1;
        }
    }
}
