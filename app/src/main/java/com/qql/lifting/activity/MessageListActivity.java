package com.qql.lifting.activity;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseListActivity;
import com.qql.lifting.adapter.MessageAdapter;
import com.qql.lifting.mvp.module.entity.Message;
import com.qql.lifting.mvp.presenter.MessageListPresenter;
import com.qql.lifting.mvp.contract.MessageListContract;

public class MessageListActivity extends BaseListActivity<Message, MessageListContract.View, MessageListPresenter> {

    @Override
    protected BaseQuickAdapter<Message, ? extends BaseViewHolder> initAdapter() {
        return new MessageAdapter(mDataList);
    }

    @Override
    protected void loadData() {

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_list_message;
    }

    @Override
    protected MessageListPresenter getPresenter() {
        return null;
    }

}
