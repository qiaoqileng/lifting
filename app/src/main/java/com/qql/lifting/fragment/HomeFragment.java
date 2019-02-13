package com.qql.lifting.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.qql.lifting.R;
import com.qql.lifting.activity.SearchActivity;
import com.qql.lifting.impl.GlideImageLoader;
import com.qql.lifting.mvp.base.Page;
import com.qql.lifting.mvp.contract.HomeContract;
import com.qql.lifting.mvp.module.entity.BannerBean;
import com.qql.lifting.mvp.presenter.HomePresenter;
import com.qql.lifting.utils.BannerUtils;
import com.qql.mylib.fragment.base.BaseFragment;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.swipe)
    SwipeRefreshLayout refreshLayout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout.setOnRefreshListener(()->{
            refreshAll();
            refreshLayout.setRefreshing(false);
        });
        mPresenter.getBanner();
    }

    @OnClick(R.id.search)
    public void search(View view){
        startActivity(new Intent(getActivity(), SearchActivity.class));
    }

    private void refreshAll() {
        mPresenter.getBanner();
        // TODO: 2019/1/30
    }

    @Override
    public void dealBanner(Page<BannerBean> banners) {
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BannerUtils.transformImgs(banners.getData()));
        banner.setDelayTime(5000);
        banner.start();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }
}
