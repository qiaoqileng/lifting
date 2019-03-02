package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.contract.MessageListContract;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;

import cn.jpush.im.android.api.JMessageClient;

public class MessageListPresenter extends BasePresenter<MessageListContract.View> implements MessageListContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();

    @Override
    public void getHistory() {
        JMessageClient.getConversationListByDefault();
    }

    @Override
    public void sendMsg() {

    }

    @Override
    public void reciveMsg() {

    }
}
