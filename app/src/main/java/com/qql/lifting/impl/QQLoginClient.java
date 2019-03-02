package com.qql.lifting.impl;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.qql.lifting.App;
import com.qql.lifting.constant.LoginType;
import com.qql.lifting.helper.LoginClientHelper;
import com.qql.lifting.helper.SPHelper;
import com.qql.lifting.interfaces.IOpenLoginClient;
import com.qql.lifting.mvp.base.BaseSubscriber;
import com.qql.lifting.mvp.contract.LoginContract;
import com.qql.lifting.mvp.module.entity.User;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.lifting.utils.LogUtil;
import com.qql.mylib.utils.RxUtil;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;

public class QQLoginClient implements IOpenLoginClient {
    private ApiService apiService = RetrofitFactory.getApiService();
    private LoginContract.View mView;
    private Tencent client = LoginClientHelper.getQQInstance();
    private CompositeDisposable mCompositeDisposable;

    public QQLoginClient(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void login(Activity activity) {
        SPHelper helper = SPHelper.getInstance(App.getApplication());
        String openId = helper.get(LoginType.KEY_OPEN_ID,"");
        String token = helper.get(LoginType.KEY_TOKEN,"");
        String expressIn = helper.get(LoginType.KEY_EXPIRES_IN,"");
        IUiListener listener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                LogUtil.d(o);
                if (o instanceof JSONObject){
                    try {
                        LoginClientHelper.saveQQOpenIdToken((JSONObject) o);
                    } catch (JSONException e) {
                        LogUtil.printException(e);
                    }
                    getInfo();
                }
            }

            @Override
            public void onError(UiError uiError) {
                mView.handleError(new Exception(uiError.errorMessage));
            }

            @Override
            public void onCancel() {
                mView.handleError(new Exception("已取消"));
            }
        };
        if (!client.isSessionValid()){
            if (!TextUtils.isEmpty(openId) && !TextUtils.isEmpty(token) && !TextUtils.isEmpty(expressIn)){
                client.setOpenId(openId);
                client.setAccessToken(token,expressIn);
            }
            client.login(activity,"all",listener);
        }
    }

    @Override
    public void login(Fragment fragment) {

    }

    @Override
    public void logout() {
        client.logout(App.getApplication());
    }

    @Override
    public void getInfo() {
        if (client != null && client.isSessionValid()){
            UserInfo info = new UserInfo(App.getApplication(),client.getQQToken());
            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    if (o instanceof JSONObject){
                        JSONObject jsonObject = (JSONObject) o;
                        try {
                            String nickname = jsonObject.getString("nickname");
                            String headUrl = jsonObject.getString("figureurl_qq_2");
                            String openId = SPHelper.getInstance(App.getApplication()).get(LoginType.KEY_OPEN_ID,"");
                            Map<String,String> params = new HashMap<>();
                            params.put("user.name",nickname);
                            params.put("user.head_url",headUrl);
                            params.put("user.open_id",openId);
                            if (mCompositeDisposable == null){
                                mCompositeDisposable = new CompositeDisposable();
                            }
                            mCompositeDisposable.clear();
                            mCompositeDisposable.add(apiService.loginOrRegirst(params).compose(RxUtil.applySchedulers())
                                .subscribeWith(new BaseSubscriber<User>(mView) {
                                    @Override
                                    protected void onSuccess(User user) {
                                        mView.dealUser(user);
                                    }
                                }));

                        }catch (Exception e){
                            LogUtil.printException(e);
                        }
                    }
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });
        }
    }
}
