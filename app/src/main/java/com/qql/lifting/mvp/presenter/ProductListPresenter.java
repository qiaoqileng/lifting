package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.base.BaseSubscriber;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.ProductListContract;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.mylib.mvp.base.BasePresenter;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.utils.RxUtil;

import java.util.HashMap;
import java.util.Map;

public class ProductListPresenter extends BasePresenter<IBaseListView<Product>> implements ProductListContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();
    @Override
    public void list(String searchName,int page,int pageSize) {
        Map<String, String> params = new HashMap<>();
        params.put("name",searchName);
        params.put("page",page +"");
        params.put("pageSize",pageSize +"");
        addSubscribe(apiService.searchProduct(params).compose(RxUtil.applySchedulers()).subscribeWith(new BaseSubscriber<Page<Product>>(mView) {
            @Override
            protected void onSuccess(Page<Product> productPage) {
                mView.handleData(productPage.getData(),null);
            }
        }));
    }
}
