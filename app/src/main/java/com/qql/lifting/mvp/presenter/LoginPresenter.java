package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.contract.LoginContract;
import com.qql.mylib.mvp.base.BasePresenter;

import java.util.Map;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void login(Map<String, String> params) {

    }
}
