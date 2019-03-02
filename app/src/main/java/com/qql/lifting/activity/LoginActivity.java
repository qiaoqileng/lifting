package com.qql.lifting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.config.UserConfig;
import com.qql.lifting.constant.LoginType;
import com.qql.lifting.helper.LoginClientHelper;
import com.qql.lifting.mvp.contract.LoginContract;
import com.qql.lifting.mvp.module.entity.User;
import com.qql.lifting.mvp.presenter.LoginPresenter;
import com.qql.lifting.utils.LogUtil;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("login");
    }

    @Override
    public void dealUser(User user) {
        UserConfig.saveUser(user);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN && resultCode == Activity.RESULT_OK){
            Tencent.onActivityResultData(requestCode, resultCode, data, new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    LogUtil.d(o);
                    if (o instanceof JSONObject){
                        try {
                            LoginClientHelper.saveQQOpenIdToken((JSONObject) o);
                        } catch (JSONException e) {
                            LogUtil.printException(e);
                        }
                        mPresenter.getInfo();
                    }
                }

                @Override
                public void onError(UiError uiError) {
                    toast(uiError.errorMessage);
                }

                @Override
                public void onCancel() {
                    toast("cancel");
                }
            });
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked(View v) {
        mPresenter.login(LoginType.TYPE_QQ);
    }
}
