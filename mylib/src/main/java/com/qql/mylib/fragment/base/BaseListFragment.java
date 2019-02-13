package com.qql.mylib.fragment.base;

import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.qql.mylib.R;
import com.qql.mylib.R2;
import com.qql.mylib.mvp.base.IBaseListView;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.widget.EmptyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author xupj
 * e-mail xupingjie@hztianque.com
 * time   2018/10/19
 * desc   下拉刷新及上拉加载的baseListFragment
 */
public abstract class BaseListFragment<data, V extends IBaseListView<data>, P extends IBasePresenter<V>> extends BaseFragment<V, P> implements IBaseListView<data>, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    /**
     * 刷新顶部扩展  子布局可以重新设置属性
     */
    @BindView(R2.id.top_extend_container)
    protected FrameLayout topExtendContainer;
    /**
     * 数据展示列表
     */
    @BindView(R2.id.rv_list)
    RecyclerView recyclerView;
    /**
     * 下拉刷新上拉加载空间  此处只使用改控件的下拉刷新功能
     */
    @BindView(R2.id.srl_refresh)
    protected SmartRefreshLayout sflRefresh;
    /**
     * recyclerView adapter基类 本身具有实现上拉加载下拉刷新功能
     * 此处只使用上拉加载功能
     */
    private BaseQuickAdapter<data, ? extends BaseViewHolder> mAdapter;

    @IntRange(from = 1)
    private int page = 1;  // 页数
    /**
     * 默认一页加载的数量
     */
    private static final int DEFAULT_PAGE_SIZE = 15;

    private int pageSize = DEFAULT_PAGE_SIZE;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // 防止加载数据时mAdapter 为null的情况
        configAdapter();
        super.onViewCreated(view, savedInstanceState);
        sflRefresh.setEnabled(isSwipeRefreshEnable());
        sflRefresh.setOnRefreshListener(refreshLayout -> refreshData());
    }

    /**
     * 刷新数据
     */
    protected void refreshData() {
        page = 1;
        loadData();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.plugin_fragmet_base_list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 创建adapter实例
     *
     * @return adapter实例
     */
    protected abstract BaseQuickAdapter<data, ? extends BaseViewHolder> createAdapter();

    /**
     * 配置adapter
     */
    private void configAdapter() {
        mAdapter = createAdapter();
        if (mAdapter == null) {
            return;
        }
        // 是否使能加载更多
        mAdapter.setEnableLoadMore(isLoadMoreEnable());
        mAdapter.setLoadMoreView(new SimpleLoadMoreView());
        // 设置Item点击监听
        mAdapter.setOnItemClickListener(this);
        // 设置item子类点击监听
        mAdapter.setOnItemChildClickListener(this);
        RecyclerView.ItemDecoration itemDecoration = createItemDecoration();
        if (itemDecoration != null) {
            recyclerView.addItemDecoration(createItemDecoration());
        }
        recyclerView.setLayoutManager(createRecyclerLayoutManager());
        // 绑定RecyclerView
        mAdapter.bindToRecyclerView(recyclerView);
        // 设置默认预加载数目
        mAdapter.setPreLoadNumber(3);
        if (isLoadMoreEnable()) {
            mAdapter.setOnLoadMoreListener(() -> {
                // 下拉刷新会重置page = 1 ，但是下拉刷新失败 此时已经加载了很多页数据
                // 再次上拉加载会导致数据错乱 ， 此处加一层判断恢复 page值
                if (page == 1) {
                    int curPage = mAdapter.getData().size() / pageSize;
                    if (curPage > 1) {
                        page = curPage;
                    }
                }
                loadData(++page, pageSize);
            }, recyclerView);
        }
    }

    @Override
    public void handleData(List<data> result, Exception e) {
        if (isDetached()) {
            return;
        }

        if (!sflRefresh.isEnableRefresh()) {
            sflRefresh.setEnableRefresh(true);
        }

        // 如果下拉监听进度条正在执行 则隐藏进度
        if (sflRefresh != null && sflRefresh.isRefreshing()) {
            sflRefresh.finishRefresh();
        }

        if (mAdapter == null) {
            return;
        }

        result = result == null ? new ArrayList<>() : result;

        if (page == 1) {
            if (e == null) {
                mAdapter.setNewData(result);
                recyclerView.scrollToPosition(0);
                if (result.size() < pageSize) {
                    mAdapter.loadMoreEnd();
                }
                if (mAdapter.getData().size() == 0) {
                    // 第一页没有数据
                    showNoDataEmptyView(mAdapter);
                }
            } else {
                // 第一页加载错误
                if (mAdapter.getData().size() == 0){
                    sflRefresh.setEnableRefresh(false);
                    showLoadErrorEmptyView(mAdapter);
                }
                // mAdapter.loadMoreFail();y
            }
        } else {
            if (e != null) {
                page--;
                mAdapter.loadMoreFail();
            } else {
                mAdapter.addData(result);
                recyclerView.invalidateItemDecorations();
                if (result.size() < pageSize) {
                    mAdapter.loadMoreEnd();
                } else {
                    mAdapter.loadMoreComplete();
                }
            }
        }
    }

    /**
     * 自定义recyclerview 布局管理器
     *
     * @return 创建recyclerView 布局管理器实例
     */
    protected RecyclerView.LayoutManager createRecyclerLayoutManager() {
        return new LinearLayoutManager(getContext());
    }


    /**
     * 是否可以上拉加載  默认true可以加载更多
     * 不需要上拉加载功能设置未false 设置为adapter
     */
    protected boolean isLoadMoreEnable() {
        return true;
    }

    /**
     * adapter 默认实现adapter item点击事件 子类只需要复写
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    /**
     * adapter 默认实现adapter item点击事件 子类只需要复写
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    public boolean isSwipeRefreshEnable() {
        return true;
    }

    /**
     * 默认加载第一页数据
     */
    @Override
    protected void loadData() {
        super.loadData();
        // 此处判断为第一次加载数据 因为第一次加载数据默认不会弹出进度框
        if (!sflRefresh.isRefreshing()) {
            //  TODO: 2018/10/23  第一次加载数据处理
            //  showWaiting();
            // 防止和 加载界面冲突
            // 正在加载的时候失能下拉刷新
            sflRefresh.setEnableRefresh(false);
            showLoadEmptyView(mAdapter);
        }
        loadData(page, pageSize);
    }

    /**
     * 默认加载 page 页的数据
     *
     * @param page     分页加载第几页数据
     * @param pageSize 每页加载数据的数量
     */
    protected void loadData(int page, int pageSize) {

    }

    /**
     * 设置分割线
     *
     * @return 设置RecyclerView的水平分割线
     */
    protected RecyclerView.ItemDecoration createItemDecoration() {
        if (getContext() == null) {
            return null;
        }
        return new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
    }

    /**
     * 第一页正在加载时显示的进度布局view
     */
    protected void showLoadEmptyView(BaseQuickAdapter<data, ? extends BaseViewHolder> adapter) {
        if (adapter != null) {
            adapter.setEmptyView(new EmptyView(getContext(), EmptyView.LOADING_TYPE));
        }
    }

    /**
     * 第一页加载错误显示的空布局
     */
    protected void showLoadErrorEmptyView(BaseQuickAdapter<data, ? extends BaseViewHolder> adapter) {
        // 显示错误布局时失能下拉刷新
        EmptyView errorView = new EmptyView(getContext(), EmptyView.ERROR_TYPE);
        errorView.setRefreshButton(R.string.plugin_empty_view_reload, R.drawable.plugin_ic_refresh, R.color.plugin_color_red, R.drawable.plugin_btn_edge);
        errorView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        errorView.setOnRefreshButtonClickListener(v -> loadData());
        if (adapter != null) {
            adapter.setEmptyView(errorView);
        }
    }

    /**
     * 第一页数据为空是显示布局
     */
    protected void showNoDataEmptyView(BaseQuickAdapter<data, ? extends BaseViewHolder> adapter) {
        if (mAdapter != null) {
            EmptyView emptyView = new EmptyView(getContext(), EmptyView.EMPTY_TYPE);
            emptyView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
            adapter.setEmptyView(emptyView);
        }
    }
}
