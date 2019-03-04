package com.qql.lifting.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.Goods;

import java.util.List;

import butterknife.ButterKnife;

public class GoodsAdapter extends BaseQuickAdapter<Goods, GoodsAdapter.Holder> {
    public GoodsAdapter() {
        super(R.layout.rv_item_goods);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void convert(Holder helper, Goods item) {

    }

    class Holder extends BaseViewHolder {

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
