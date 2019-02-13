package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.AppVersion;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

public interface MainContract {
    interface View extends IBaseView{
        void getVersion(AppVersion version);
    }

    interface Presenter extends IBasePresenter<View>{
        void checkVersion();
    }
}
