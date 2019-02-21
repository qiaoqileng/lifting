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
        super(R.layout.rv_item_remind);
    }

    @Override
    protected void convert(Holder helper, Remind item) {
        helper.preImg.setImageDrawable(ApkUtil.getIconByPkg(mContext,item.getPkg()));
        helper.price.setText(item.getTitle());
        helper.currType.setText(item.getContent());
    }

    class Holder extends BaseViewHolder {
        @BindView(R.id.pre_img)
        ImageView preImg;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.curr_type)
        TextView currType;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
