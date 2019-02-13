package com.qql.mylib.mvp.base;

public interface IBasePresenter<V> {
    void attach(V var1);
    void detach();
}