package com.qql.lifting.mvp.contract;

import com.qql.lifting.mvp.module.entity.Message;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;

import java.util.List;

public interface MessageListContract {
    interface View extends IBaseListView<Message> {
        void dealHistory(List<Message> messages);
        void dealSendResult(boolean result,Message message);
        void dealReciveMsg(boolean result,Message message);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHistory();
        void sendMsg();
        void reciveMsg();
    }
}
