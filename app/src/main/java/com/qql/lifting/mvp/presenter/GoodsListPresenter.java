package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.contract.GoodsListContract;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;

public class GoodsListPresenter extends BasePresenter<GoodsListContract.View> implements GoodsListContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();
}
