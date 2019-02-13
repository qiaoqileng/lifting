package com.qql.mylib.mvp.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by yxf on 2017/8/4.
 */

public abstract class BasePresenter<T extends IBaseView> implements IBasePresenter<T>{
    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    //移除所有的disposable
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    //移除某一个disposable
    public void removeDispose(Disposable d) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.remove(d);
        }
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void attach(T view) {
        this.mView = view;
    }

    public void detach() {
        unSubscribe();
        if (this.mView != null) {
            this.mView = null;
        }
    }

}
