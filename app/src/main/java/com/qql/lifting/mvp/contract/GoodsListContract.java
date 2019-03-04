package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Goods;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;

public interface GoodsListContract {
    interface View extends IBaseListView<Goods> {
    }

    interface Presenter extends IBasePresenter<View> {
    }
}
