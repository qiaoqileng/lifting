package com.qql.lifting.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.Remind;
import com.qql.lifting.utils.ApkUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RemindAdapter extends BaseQuickAdapter<Remind, RemindAdapter.Holder> {

    public RemindAdapter() {
        super(R.layout.rv_item_app_info);
    }

    @Override
    protected void convert(Holder helper, Remind item) {
        helper.ivHead.setImageDrawable(ApkUtil.getIconByPkg(mContext, item.getPkg()));
        helper.title.setText(item.getTitle());
        helper.content.setText(item.getContent());
    }

    class Holder extends BaseViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
