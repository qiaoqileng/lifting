package com.qql.lifting.mvp.presenter;

import com.qql.lifting.mvp.contract.MainContract;
import com.qql.mylib.mvp.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{
    @Override
    public void checkVersion() {
        // TODO: 2019/1/29 check update
    }

}
