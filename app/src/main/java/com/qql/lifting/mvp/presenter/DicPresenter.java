package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.base.BaseSubscriber;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.DicContract;
import com.qql.lifting.mvp.contract.DicContract;
import com.qql.lifting.mvp.module.entity.BannerBean;
import com.qql.lifting.mvp.module.entity.Dic;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;
import com.qql.mylib.utils.RxUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DicPresenter extends BasePresenter<DicContract.View> implements DicContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();
    @Override
    public void getDics(String key) {
        Map<String,String> params = new HashMap<>();
        params.put("domain",key);
        addSubscribe(apiService.getDicsById(params).compose(RxUtil.applySchedulers())
                .subscribeWith(new BaseSubscriber<List<Dic>>(mView) {

                    @Override
                    protected void onSuccess(List<Dic> pageBaseJsonResponse) {
                        if (mView != null) {
                            mView.dealDics(pageBaseJsonResponse);
                        }
                    }
                }));
    }
}
