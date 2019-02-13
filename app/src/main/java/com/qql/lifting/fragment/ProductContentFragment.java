package com.qql.lifting.fragment;

import com.qql.lifting.R;
import com.qql.mylib.fragment.base.BaseFragment;
import com.qql.mylib.mvp.base.IBasePresenter;

public class ProductContentFragment extends BaseFragment {
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_product_content;
    }

    @Override
    protected IBasePresenter getPresenter() {
        return null;
    }
}
