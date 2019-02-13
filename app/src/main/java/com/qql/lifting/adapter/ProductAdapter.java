package com.qql.lifting.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.Product;
import com.qql.lifting.option.GlideOptions;

import java.util.List;

public class ProductAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {
    public ProductAdapter(List<Product> mDataList) {
        super(R.layout.rv_item_product,mDataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        helper.setText(R.id.tv_brand_name,item.getBrand().getName());
        helper.setText(R.id.tv_name,item.getContent());
        helper.setText(R.id.tv_price,item.getPrice() + "");
        helper.setText(R.id.tv_shop_name,item.getShop().getName());
        Glide.with(mContext).asBitmap().load(item.getHeadImg()).apply(GlideOptions.defaultOption()).into(((ImageView)helper.getView(R.id.iv_head)));
    }

}
