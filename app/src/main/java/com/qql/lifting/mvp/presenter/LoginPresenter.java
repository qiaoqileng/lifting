package com.qql.lifting.mvp.presenter;

import android.app.Activity;

import com.qql.lifting.App;
import com.qql.lifting.constant.LoginType;
import com.qql.lifting.helper.SPHelper;
import com.qql.lifting.impl.QQLoginClient;
import com.qql.lifting.interfaces.IOpenLoginClient;
import com.qql.lifting.mvp.contract.LoginContract;
import com.qql.mylib.mvp.base.BasePresenter;

import java.util.Map;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private QQLoginClient client;

    @Override
    public void login(int type) {
        if (type == LoginType.TYPE_QQ){
            client = new QQLoginClient(mView);
        }
        SPHelper.getInstance(App.getApplication()).put(LoginType.KEY_TYPE,type);

        if (client != null){
            client.login((Activity) mView);
        }
    }

    @Override
    public void getInfo() {
        if (client != null) {
            client.getInfo();
        }
    }
}
