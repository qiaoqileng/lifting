package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.contract.FriendListContract;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;

public class FriendListPresenter extends BasePresenter<FriendListContract.View> implements FriendListContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();
}
