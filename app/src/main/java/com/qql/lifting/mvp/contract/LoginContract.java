package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.User;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.util.Map;

public interface LoginContract {
    interface View extends IBaseView {
        void dealUser(User banner);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(Map<String,String> params);
    }
}
