package com.qql.mylib.widget;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.qql.mylib.R;

/**
 * Created by yxf on 2017/3/31.
 */

public class SimpleLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
