package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.module.entity.SimpleProduct;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.util.List;

public interface SearchContract {
    interface View extends IBaseView{
        void dealHot(Page<SimpleProduct> products);
        void dealHistory(List<SimpleProduct> products);
        void dealSaveResult(boolean success);
        void dealClearResult(boolean success);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHot();
        void getHistory();
        void saveHistory(SimpleProduct product);
        void clearHistory();
    }
}
