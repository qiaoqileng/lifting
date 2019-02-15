package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Dic;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.util.List;

public interface DicContract {
    interface View extends IBaseView {
        void dealDics(List<Dic> banner);
    }

    interface Presenter extends IBasePresenter<View> {
        void getDics(String key);
    }
}
