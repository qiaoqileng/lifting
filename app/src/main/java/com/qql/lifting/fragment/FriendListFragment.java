package com.qql.lifting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.contract.FriendListContract;
import com.qql.lifting.mvp.module.entity.Friend;
import com.qql.lifting.mvp.presenter.FriendListPresenter;
import com.qql.mylib.fragment.base.BaseListFragment;

public class FriendListFragment extends BaseListFragment<Friend, FriendListContract.View, FriendListPresenter> implements FriendListContract.View {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_list_friend;
    }

    @Override
    protected BaseQuickAdapter<Friend, ? extends BaseViewHolder> createAdapter() {
        return null;
    }

    @Override
    protected FriendListPresenter getPresenter() {
        return new FriendListPresenter();
    }
}
