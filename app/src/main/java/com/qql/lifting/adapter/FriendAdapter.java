package com.qql.lifting.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.Friend;

import java.util.List;

import butterknife.ButterKnife;

public class FriendAdapter extends BaseQuickAdapter<Friend, FriendAdapter.Holder> {
    public FriendAdapter(List<Friend> mDataList) {
        super(R.layout.rv_item_friend);
    }

    @Override
    protected void convert(Holder helper, Friend item) {

    }

    class Holder extends BaseViewHolder {

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
