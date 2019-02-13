package com.qql.lifting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseActivity;
import com.qql.lifting.adapter.TagItemAdapter;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.SearchContract;
import com.qql.lifting.mvp.module.entity.SimpleProduct;
import com.qql.lifting.mvp.presenter.SearchPresenter;
import com.qql.lifting.utils.LogUtil;
import com.qql.lifting.utils.Utils;
import com.qql.lifting.view.decoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchContract.View, SearchPresenter> implements SearchContract.View, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recycler_hot)
    RecyclerView recyclerViewHot;
    @BindView(R.id.recycler_history)
    RecyclerView recyclerViewHistory;
    @BindView(R.id.search)
    EditText search;
    private TagItemAdapter hotAdapter;
    private TagItemAdapter historyAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        mPresenter.getHot();
        mPresenter.getHistory();
    }

    private void initView() {
        search.setOnEditorActionListener((v, actionId, event) -> {
            String key = v.getText().toString();
            LogUtil.d("搜索框内容：" + key);
            if (!TextUtils.isEmpty(key)){
                mPresenter.saveHistory(new SimpleProduct(key));
                toProductList(key);
            }
            return true;
        });
        FlexboxLayoutManager layoutManagerHot = new FlexboxLayoutManager(this);
        layoutManagerHot.setFlexDirection(FlexDirection.ROW);
        layoutManagerHot.setJustifyContent(JustifyContent.FLEX_START);
        recyclerViewHot.setLayoutManager(layoutManagerHot);
        recyclerViewHot.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_4)));

        hotAdapter = new TagItemAdapter();
        hotAdapter.bindToRecyclerView(recyclerViewHot);
        hotAdapter.setOnItemClickListener(this);

        FlexboxLayoutManager layoutManagerHistory = new FlexboxLayoutManager(this);
        layoutManagerHistory.setFlexDirection(FlexDirection.ROW);
        layoutManagerHistory.setJustifyContent(JustifyContent.FLEX_START);
        recyclerViewHistory.setLayoutManager(layoutManagerHistory);
        recyclerViewHistory.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.dp_4)));

        historyAdapter = new TagItemAdapter();
        historyAdapter.bindToRecyclerView(recyclerViewHistory);
        historyAdapter.setOnItemClickListener(this);
    }

    private void toProductList(String key) {
        Intent intent = new Intent(this,ProductListActivity.class);
        intent.putExtra(ProductListActivity.KEY_SEARCH_NAME,key);
        startActivity(intent);
    }

    @OnClick(R.id.delete)
    public void deleteHistory(View view){
        mPresenter.clearHistory();
    }

    @Override
    public void dealHot(Page<SimpleProduct> products) {
        hotAdapter.replaceData(products.getData());
    }

    @Override
    public void dealHistory(List<SimpleProduct> products) {
        if (!Utils.isEmptyList(products)){
            historyAdapter.replaceData(products);
        } else {
            historyAdapter.replaceData(new ArrayList<>());
        }
    }

    @Override
    public void dealSaveResult(boolean success) {
        if (success){
            mPresenter.getHistory();
        }
    }

    @Override
    public void dealClearResult(boolean success) {
        if (success){
            mPresenter.getHistory();
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter getPresenter() {
        return new SearchPresenter();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (view instanceof TextView) {
            String key = ((TextView) view).getText().toString();
            mPresenter.saveHistory(new SimpleProduct(key));
            toProductList(key);
        }
    }
}
