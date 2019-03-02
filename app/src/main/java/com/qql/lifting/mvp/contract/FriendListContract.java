package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Friend;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;

public interface FriendListContract {
    interface View extends IBaseListView<Friend> {
    }

    interface Presenter extends IBasePresenter<View> {
    }
}
