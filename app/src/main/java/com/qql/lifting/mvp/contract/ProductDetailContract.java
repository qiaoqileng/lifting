package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Product;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

public interface ProductDetailContract {
    interface View extends IBaseView{
        void dealData(Product product);
    }
    interface Presenter extends IBasePresenter<View> {
        void getDetail(long id);
    }
}
