package com.qql.lifting.mvp.presenter;

import com.qql.lifting.db.manager.GreenDaoManager;
import com.qql.lifting.mvp.contract.RemindAddContract;
import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.mylib.mvp.base.BasePresenter;

public class RemindAddPresenter extends BasePresenter<RemindAddContract.View> implements RemindAddContract.Presenter {
    @Override
    public void add(Remind remind) {
        long id = GreenDaoManager.getDaoSession().getRemindDao().insert(remind);
        mView.dealResult(id > 0);
    }
}
