package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.base.BaseSubscriber;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.HomeContract;
import com.qql.lifting.mvp.module.entity.BannerBean;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;
import com.qql.mylib.utils.RxUtil;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();
    @Override
    public void getBanner() {
        addSubscribe(apiService.getBanner().compose(RxUtil.applySchedulers())
                .subscribeWith(new BaseSubscriber<Page<BannerBean>>(mView) {

                    @Override
                    protected void onSuccess(Page<BannerBean> pageBaseJsonResponse) {
                        if (mView != null) {
                            mView.dealBanner(pageBaseJsonResponse);
                        }
                    }
                }));
    }
}
