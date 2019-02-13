package com.qql.lifting.mvp.base;

import com.qql.lifting.config.UserConfig;
import com.qql.mylib.mvp.base.BaseLibSubscriber;
import com.qql.mylib.mvp.base.IBaseView;

public abstract class BaseSubscriber<T> extends BaseLibSubscriber<T> {
    protected BaseSubscriber(IBaseView baseView) {
        super(baseView);
    }

    protected BaseSubscriber(IBaseView baseView,boolean showDialog){
        super(baseView,showDialog);
    }

    @Override
    protected boolean isLogin() {
        return UserConfig.isLogin();
    }

    @Override
    protected boolean needSession() {
        return false;
    }
}
