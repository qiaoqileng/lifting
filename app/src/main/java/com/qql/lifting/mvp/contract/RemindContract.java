package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.util.Map;

public interface RemindContract {
    interface View extends IBaseView{
        void dealData(Page<Remind> remindPage);
    }

    interface Presenter extends IBasePresenter<View>{
        void getRemind(Map<String,String> params);
    }
}
