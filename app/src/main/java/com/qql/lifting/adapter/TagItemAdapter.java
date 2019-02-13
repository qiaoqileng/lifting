package com.qql.lifting.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.SimpleProduct;

public class TagItemAdapter extends BaseQuickAdapter<SimpleProduct, TagItemAdapter.TagViewHolder> {

    public TagItemAdapter() {
        super(R.layout.item_tag);
    }

    @Override
    protected void convert(TagViewHolder helper, SimpleProduct item) {
        helper.setText(R.id.content,item.getName());
    }

    class TagViewHolder extends BaseViewHolder {
        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
