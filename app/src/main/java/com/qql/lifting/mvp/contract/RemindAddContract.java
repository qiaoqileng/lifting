package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

public interface RemindAddContract {
    interface View extends IBaseView {
        void dealResult(boolean success,Remind remind);
    }

    interface Presenter extends IBasePresenter<View> {
        void add(Remind remind);
    }
}
