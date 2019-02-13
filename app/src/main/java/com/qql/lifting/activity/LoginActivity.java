package com.qql.lifting.activity;

import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.mvp.contract.LoginContract;
import com.qql.lifting.mvp.module.entity.User;
import com.qql.lifting.mvp.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    @Override
    protected int getContentLayoutId() {
        return 0;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void dealUser(User banner) {

    }
}
