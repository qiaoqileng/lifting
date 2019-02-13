package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.base.BaseSubscriber;
import com.qql.lifting.mvp.contract.ProductDetailContract;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;
import com.qql.mylib.utils.RxUtil;

import java.util.HashMap;
import java.util.Map;

public class ProductDetailPresenter extends BasePresenter<ProductDetailContract.View> implements ProductDetailContract.Presenter{
    private ApiService apiService = RetrofitFactory.getApiService();
    @Override
    public void getDetail(long id) {
        Map<String,String> params = new HashMap<>();
        params.put("id",id + "");
        addSubscribe(apiService.getProductById(params).compose(RxUtil.applySchedulers())
        .subscribeWith(new BaseSubscriber<Product>(mView, true) {
            @Override
            protected void onSuccess(Product product) {
                mView.dealData(product);
            }
        }));
    }
}
