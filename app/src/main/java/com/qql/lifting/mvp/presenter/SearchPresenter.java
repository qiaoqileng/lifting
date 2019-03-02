package com.qql.lifting.mvp.presenter;

import com.qql.lifting.constant.Constants;
import com.qql.lifting.db.manager.GreenDaoManager;
import com.qql.lifting.mvp.base.BaseSubscriber;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.SearchContract;
import com.qql.lifting.mvp.module.entity.SimpleProduct;
import com.qql.lifting.mvp.module.network.ApiService;
import com.qql.lifting.mvp.module.network.RetrofitFactory;
import com.qql.lifting.utils.Utils;
import com.qql.mylib.mvp.base.BasePresenter;
import com.qql.mylib.utils.RxUtil;

import java.util.List;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    private ApiService apiService = RetrofitFactory.getApiService();

    @Override
    public void getHot() {
        addSubscribe(apiService.getHot().compose(RxUtil.applySchedulers())
                .subscribeWith(new BaseSubscriber<Page<SimpleProduct>>(mView) {
                    @Override
                    protected void onSuccess(Page<SimpleProduct> simpleProductPage) {
                        if (mView != null) {
                            mView.dealHot(simpleProductPage);
                        }
                    }
                }));
    }

    @Override
    public void getHistory() {
        List<SimpleProduct> list = null;
        try {
            list = GreenDaoManager.getDaoSession().getSimpleProductDao().queryBuilder().limit(Constants.HISTORY_ROWS).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (!Utils.isEmptyList(list) && mView != null) {
            mView.dealHistory(list);
        }
    }

    @Override
    public void saveHistory(SimpleProduct product) {
        if (product != null) {
            GreenDaoManager.getDaoSession().getSimpleProductDao().save(product);
            mView.dealSaveResult(true);
        } else {
            mView.dealSaveResult(false);
        }
    }

    @Override
    public void clearHistory() {
        GreenDaoManager.getDaoSession().getSimpleProductDao().deleteAll();
        mView.dealClearResult(true);
    }
}
