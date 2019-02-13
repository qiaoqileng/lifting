package com.qql.lifting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.activity.base.BaseListActivity;
import com.qql.lifting.adapter.ProductAdapter;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.mvp.presenter.ProductListPresenter;
import com.qql.mylib.mvp.base.IBaseListView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qql.lifting.activity.ProductDetailActivity.KEY_PRODUCT_ID;

public class ProductListActivity extends BaseListActivity<Product, IBaseListView<Product>,ProductListPresenter> {
    public static final String KEY_SEARCH_NAME = "key_search_name";
    private String name;
    @BindView(R.id.search)
    TextView search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getIntent().getStringExtra(KEY_SEARCH_NAME);

        initView();
    }

    @Override
    protected BaseQuickAdapter<Product, BaseViewHolder> initAdapter() {
        return new ProductAdapter(mDataList);
    }

    @Override
    protected void loadData() {
        mPresenter.list(name,page,pageSize);
    }

    private void initView() {
        search.setText(name);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof ProductAdapter){
            Long id = ((Product)adapter.getItem(position)).getId();
            toDetail(id);
        }
        super.onItemClick(adapter, view, position);
    }

    private void toDetail(Long id) {
        Intent intent = new Intent(this,ProductDetailActivity.class);
        intent.putExtra(KEY_PRODUCT_ID,id);
        startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected ProductListPresenter getPresenter() {
        return new ProductListPresenter();
    }

    @OnClick({R.id.back,R.id.search})
    public void onClick(View v){
        finish();
    }
}
