package com.qql.lifting.mvp.presenter;

import com.qql.lifting.App;
import com.qql.lifting.mvp.contract.AppInfoContract;
import com.qql.lifting.mvp.module.entity.AppInfo;
import com.qql.lifting.utils.ApkUtil;
import com.qql.mylib.mvp.base.BasePresenter;
import com.qql.mylib.mvp.base.IBaseListView;

public class AppInfoPresenter extends BasePresenter<IBaseListView<AppInfo>> implements AppInfoContract.Presenter {
    @Override
    public void getAppInfo() {
        mView.handleData(ApkUtil.GetAppList(App.getApplication()),null);
    }
}
