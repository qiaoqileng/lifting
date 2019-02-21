package com.qql.lifting.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.App;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.AppInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, AppInfoAdapter.Holder> {

    public AppInfoAdapter(List<AppInfo> mDataList) {
        super(R.layout.rv_item_app_info,mDataList);
    }

    @Override
    protected void convert(Holder helper, AppInfo item) {
        helper.ivHead.setImageDrawable(item.getIco(App.getApplication()));
        helper.title.setText(item.getLabel());
        helper.content.setText(item.getPkg());
    }

    public class Holder extends BaseViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        public Holder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
