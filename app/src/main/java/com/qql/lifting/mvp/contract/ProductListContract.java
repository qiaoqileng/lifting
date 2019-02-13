package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Product;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;

public interface ProductListContract {

    interface Presenter extends IBasePresenter<IBaseListView<Product>> {
        void list(String searchName,int page,int pageSize);
    }
}
