package com.qql.lifting.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qql.lifting.activity.base.BaseListActivity;
import com.qql.lifting.adapter.AppInfoAdapter;
import com.qql.lifting.mvp.module.entity.AppInfo;
import com.qql.lifting.mvp.presenter.AppInfoPresenter;
import com.qql.mylib.mvp.base.IBaseListView;

public class AppInfoActivity extends BaseListActivity<AppInfo, IBaseListView<AppInfo>, AppInfoPresenter>{
    public static final String RESULT_APP_PKG = "result_app_info";

    @Override
    protected BaseQuickAdapter<AppInfo, AppInfoAdapter.Holder> initAdapter() {
        return new AppInfoAdapter(mDataList);
    }

    @Override
    protected void loadData() {
        mPresenter.getAppInfo();
    }

    @Override
    protected AppInfoPresenter getPresenter() {
        return new AppInfoPresenter();
    }

    @Override
    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Object obj = adapter.getItem(position);
        if (obj instanceof AppInfo){
            Intent data = new Intent();
            Bundle b = new Bundle();
            b.putString(RESULT_APP_PKG, ((AppInfo) obj).getPkg());
            data.putExtras(b);
            setResult(Activity.RESULT_OK,data);
            finish();
        }
    }

    @Override
    protected boolean isLoadMoreEnable() {
        return false;
    }
}
