package com.qql.lifting.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.Message;

import java.util.List;

import butterknife.ButterKnife;

public class MessageAdapter extends BaseQuickAdapter<Message, MessageAdapter.Holder> {
    public MessageAdapter(List<Message> mDataList) {
        super(R.layout.rv_item_message);
    }

    @Override
    protected void convert(Holder helper, Message item) {

    }

    class Holder extends BaseViewHolder {

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
