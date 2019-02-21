package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.AppInfo;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.util.List;

public interface AppInfoContract {

    interface Presenter extends IBasePresenter<IBaseListView<AppInfo>> {
        void getAppInfo();
    }
}
