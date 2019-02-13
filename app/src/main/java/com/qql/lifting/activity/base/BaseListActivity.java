package com.qql.lifting.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qql.lifting.R;
import com.qql.lifting.utils.Utils;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.module.network.HError;
import com.qql.mylib.widget.EmptyView;
import com.qql.mylib.widget.NetErrorView;
import com.qql.mylib.widget.SimpleLoadMoreView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yxf on 2017/4/10.
 */

public abstract class BaseListActivity<data,v, presenter extends IBasePresenter<v>> extends BaseActivity<v,presenter> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemChildLongClickListener, BaseQuickAdapter.OnItemLongClickListener, IBaseListView<data> {
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_list)
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter<data, ? extends BaseViewHolder> mAdapter;
    protected int page = 1;
    /**
     * 每次加载数据时保存上次的page
     */
    private int oldPage = page;
    /**
     * 默认每页加载10条数据
     */
    protected int pageSize = 10;
    protected List<data> mDataList = new ArrayList<>();
    protected int mAnimationType = BaseQuickAdapter.ALPHAIN;
    /**
     * 默认开启加载全部数据之后显示提示没有更多数据
     */
    protected static boolean mLoadMoreEndGone = false;

    /**
     * 默认开启还剩3条数据时开始预加载
     */
    private static final int mAutoLoadMoreSize = 3;

    @Override
    protected int getContentLayoutId() {
        return R.layout.base_list_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpAdapter();
        initParams();
        initListener();
    }

    private void setUpAdapter() {
        mAdapter = initAdapter();
        if (mAdapter != null) {
            mAdapter.setEnableLoadMore(isLoadMoreEnable());
            mAdapter.setLoadMoreView(new SimpleLoadMoreView());
            mAdapter.setOnItemClickListener(this);
            mAdapter.setOnItemLongClickListener(this);
            mAdapter.setOnItemChildClickListener(this);
            mAdapter.setOnItemChildLongClickListener(this);
            mRecyclerView.setLayoutManager(createLayoutManager());
            RecyclerView.ItemDecoration itemDecoration = createItemDecoration();
            if (itemDecoration != null) {
                mRecyclerView.addItemDecoration(itemDecoration);
            }

            switchSkeletonDate();
            mAdapter.bindToRecyclerView(mRecyclerView);

            mAdapter.setPreLoadNumber(mAutoLoadMoreSize);
            if (isLoadMoreEnable()) {
                mAdapter.setOnLoadMoreListener(() -> {
                    if(mSwipeRefreshLayout.isRefreshing()){
                        mAdapter.loadMoreComplete();
                        return;
                    }
                    if(Utils.isEmptyList(mAdapter.getData())){
                        mAdapter.loadMoreComplete();
                        return;
                    }
                    oldPage = page;
                    page++;
                    loadData();
                }, mRecyclerView);
            }
            checkToRefresh();
        }
    }

    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    private void checkToRefresh() {
        if (isSwipeRefreshEnable()) {
            mSwipeRefreshLayout.autoRefresh();
        } else {
            showWaiting();
            refreshData();
        }
    }

    /**
     * 设置分割线
     *
     * @return
     */
    protected RecyclerView.ItemDecoration createItemDecoration() {
        return null;
    }

    protected void initParams() {
        mSwipeRefreshLayout.setEnabled(isSwipeRefreshEnable());
        mEmptyView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        mEmptyView.setVisibility(View.VISIBLE);
        mErrorView.enableRefresh(v -> checkToRefresh());
    }


    protected abstract BaseQuickAdapter<data, BaseViewHolder> initAdapter();

    protected abstract void loadData();

    @Override
    public void handleData(List<data> value, Exception e) {
        if (isFinishing()) {
            return;
        }

        hideWaiting();
        if (mAdapter == null) {
            return;
        }
        if (null == value) {
            value = new ArrayList<>();
        }
        if (e instanceof HError) {
            mAdapter.setEmptyView(mErrorView);
        } else {
            mAdapter.setEmptyView(mEmptyView);
        }
        mAdapter.isUseEmpty(isUseEmptyView());
        if (page == 1) {
            if (e != null ) {
                //刷新失败
                page = oldPage;
                if(Utils.isEmptyList(mAdapter.getData())){
                    //数据置null时才能显示空视图
                    mAdapter.setNewData(null);
                }
            }
            if (e == null) {
                mAdapter.setNewData(value);
                if (!Utils.isEmptyList(value)) {
                    if (value.size() < pageSize) {
                        mAdapter.loadMoreEnd(mLoadMoreEndGone);
                    }
                }
            }
        } else {
            if (e != null) {
                page = oldPage;
                mAdapter.loadMoreFail();
            } else {
                mAdapter.addData(value);
                mRecyclerView.invalidateItemDecorations();
                if (value.size() < pageSize) {
                    mAdapter.loadMoreEnd(mLoadMoreEndGone);
                } else {
                    mAdapter.loadMoreComplete();

                }

            }
        }
    }


    /**
     * 是否可以下拉刷新
     */
    protected boolean isSwipeRefreshEnable() {
        return true;
    }

    /**
     * 是否可以上拉加載
     */
    protected boolean isLoadMoreEnable() {
        return true;
    }

    /**
     * 是否支持空佈局
     */
    protected boolean isUseEmptyView() {
        return true;
    }

    @Override
    public void handleError(Exception error) {
        super.handleError(error);
        handleData(null, error);
    }

    protected void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(refreshlayout -> refreshData());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (!isFinishing()) {
                            try {
                                Glide.with(BaseListActivity.this).resumeRequests();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        if (!isFinishing()) {
                            try {
                                Glide.with(BaseListActivity.this).pauseRequests();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        if (!isFinishing()) {
                            try {
                                Glide.with(BaseListActivity.this).resumeRequests();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }

    /**
     * 显示skeleton视图
     */
    protected void switchSkeletonDate() {
        if (!Utils.isEmptyList(mAdapter.getData())) {
            return;
        }
        List<data> skeletonInfo = getSkeletonDate();
        if (!Utils.isEmptyList(skeletonInfo)) {
            mAdapter.setNewData(skeletonInfo);
        }
    }

    protected List<data> getSkeletonDate() {
        return null;
    }

    protected void refreshData() {
//        showWaiting();
        oldPage = page;
        page = 1;
        loadData();
    }

    @Override
    public void showWaiting() {
        if (null != mSwipeRefreshLayout && !mSwipeRefreshLayout.isRefreshing() && page == 1) {
            super.showWaiting();
        }
    }

    @Override
    public void hideWaiting() {
        if (null != mSwipeRefreshLayout && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.finishRefresh();
        } else {
            super.hideWaiting();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }


    @Override
    public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

//    @Override
//    public void onTopBarClickListener() {
//        mRecyclerView.scrollToPosition(0);
//    }
//
//    @Override
//    protected void releasePlayer() {
//        super.releasePlayer();
//
//        if (mAdapter instanceof BaseMediaQuickAdapter) {
//            ((BaseMediaQuickAdapter) mAdapter).releasePlayer();
//        } else if (mAdapter instanceof BaseMediaMultiItemQuickAdapter) {
//            ((BaseMediaMultiItemQuickAdapter) mAdapter).releasePlayer();
//        }
//    }
}
