package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.module.entity.BannerBean;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.util.List;

public interface HomeContract {
    interface View extends IBaseView{
        void dealBanner(Page<BannerBean> banner);
    }

    interface Presenter extends IBasePresenter<View>{
        void getBanner();
    }
}
