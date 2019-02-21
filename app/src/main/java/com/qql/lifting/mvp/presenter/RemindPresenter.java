package com.qql.lifting.mvp.presenter;

import com.qql.lifting.db.manager.GreenDaoManager;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.RemindContract;
import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.mylib.mvp.base.BasePresenter;

import java.util.List;
import java.util.Map;

public class RemindPresenter extends BasePresenter<RemindContract.View> implements RemindContract.Presenter {
    @Override
    public void getRemind(Map<String, String> params) {
        // TODO: 2019/2/19
        List<Remind> list = GreenDaoManager.getDaoSession().getRemindDao().loadAll();
        if (mView != null){
            mView.dealData(new Page<>(list));
        }
    }
}
