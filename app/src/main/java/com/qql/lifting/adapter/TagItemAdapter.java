package com.qql.lifting.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.ITagItem;

public class TagItemAdapter extends BaseQuickAdapter<ITagItem, TagItemAdapter.TagViewHolder> {

    public TagItemAdapter() {
        super(R.layout.item_tag);
    }

    @Override
    protected void convert(TagViewHolder helper, ITagItem item) {
        helper.setText(R.id.content,item.getTagName());
    }

    class TagViewHolder extends BaseViewHolder {
        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
