package com.qql.lifting.activity;

import android.view.View;

import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.mvp.contract.LoginContract;
import com.qql.lifting.mvp.module.entity.User;
import com.qql.lifting.mvp.presenter.LoginPresenter;
import com.qql.lifting.utils.PhotoPickerUtils;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void dealUser(User banner) {

    }

    @OnClick(R.id.test)
    public void test(View view){
        PhotoPickerUtils.pickerMultiMode(this,4);
    }
}
