package com.qql.lifting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.adapter.GoodsAdapter;
import com.qql.lifting.mvp.contract.GoodsListContract;
import com.qql.lifting.mvp.module.entity.Goods;
import com.qql.lifting.mvp.presenter.GoodsListPresenter;
import com.qql.mylib.fragment.base.BaseListFragment;

import butterknife.BindView;

public class GoodsListFragment extends BaseListFragment<Goods, GoodsListContract.View, GoodsListPresenter> implements GoodsListContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_list_goods;
    }

    @Override
    protected BaseQuickAdapter<Goods, ? extends BaseViewHolder> createAdapter() {
        return new GoodsAdapter();
    }

    @Override
    protected GoodsListPresenter getPresenter() {
        return new GoodsListPresenter();
    }
}
