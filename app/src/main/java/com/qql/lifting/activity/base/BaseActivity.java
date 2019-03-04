package com.qql.lifting.activity.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.qql.lifting.App;
import com.qql.mylib.R;
import com.qql.mylib.activity.base.UMActivity;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;
import com.qql.mylib.utils.AndroidBug5497Workaround;
import com.qql.mylib.widget.EmptyView;
import com.qql.mylib.widget.NetErrorView;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Mvp - V BaseActivity基类，封装一些常用方法
 */
public abstract class BaseActivity<V ,P extends IBasePresenter<V>> extends UMActivity implements IBaseView, Toolbar.OnMenuItemClickListener {
    protected P mPresenter;

    private Unbinder mUnbinder;
    private Toast mToast;
    protected Toolbar mToolbar;
    protected KProgressHUD waitingDialog;
    protected EmptyView mEmptyView;
    protected NetErrorView mErrorView;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getContentLayoutId() != 0) {
            setContentView(getContentLayoutId());
            mUnbinder = ButterKnife.bind(this);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }

        mToolbar = getToolbar();
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(useBackNav());
            }
            mToolbar.setNavigationOnClickListener(v -> finish());
            mToolbar.setOnMenuItemClickListener(this);
            mToolbar.setTitle(getCurrTitle());
        }
        mEmptyView = new EmptyView(this, EmptyView.EMPTY_TYPE);
        mErrorView = new NetErrorView(this);
    }

    protected String getCurrTitle() {
        return getString(R.string.app_name);
    }
    /**
     * 使用返回导航图标  即标题显示返回按钮
     */
    protected boolean useBackNav() {
        return true;
    }

    /**
     * 初始化根布局文件
     *
     * @return 资源文件id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化presenter层
     *
     * @return presenter实例
     */
    protected abstract P getPresenter();

    @Override
    protected void onStop() {
        super.onStop();
        if (mToast != null) {
            // 避免Activity 不可见时 仍然弹出toast提示影响用户体验
            mToast.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        mToast = null;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    // 解决 adjustResize 或 adjustPan 输入法遮挡问题
    @SuppressWarnings("unused")
    protected void resolveInputForAdjustResize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AndroidBug5497Workaround.assistActivity(this);
        }
    }

    @SuppressLint("ShowToast")
    public void toast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            if (mToast == null) {
                mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
            }
            mToast.setText(msg);
            mToast.show();
        }
    }

    @SuppressWarnings("unused")
    protected void toast(@StringRes int msgId) {
        toast(getResources().getString(msgId));
    }


    /**
     *  使用toolbar作为标题栏 ,可以返回null，不使用Toolbar作为标题导航栏，也可以使用自定义样式Toolbar
     *
     *  布局中使用默认toolbar 必须这样调用布局 ，否则返回为null
     *  <include  android:id="@id/plugin_toolbar" layout="@layout/plugin_include_toolbar" />
     */
    protected Toolbar getToolbar() {
        return findViewById(R.id.toolBar);
    }

    @Override
    public void toLogin() {
        App.getApplication().toLogin();
    }

    public void handleError(Exception error) {
        toast(error.getMessage());
    }

    @Override
    public void showWaiting() {
        showWaiting(true, null);
    }

    @Override
    public void showWaiting(boolean cancelable, DialogInterface.OnCancelListener onCancelListener) {
        runOnUiThread(() -> {
            if (waitingDialog == null || !waitingDialog.isShowing()) {
                if (!isFinishing()) {
                    waitingDialog = KProgressHUD.create(BaseActivity.this)
                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                            .setCancellable(cancelable)
                            .setAnimationSpeed(1)
                            .setSize(60, 60)
                            .setDimAmount(0.5f)
                            .show();

                    if (onCancelListener != null) {
                        try {
                            Field mProgressDialog = waitingDialog.getClass().getDeclaredField("mProgressDialog");
                            mProgressDialog.setAccessible(true);
                            Dialog progressDialog = (Dialog) mProgressDialog.get(waitingDialog);
                            if (progressDialog != null) {
                                progressDialog.setOnCancelListener(onCancelListener);
                            }
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        });


    }


    public void hideWaiting() {
        if (waitingDialog != null && waitingDialog.isShowing()) {
            waitingDialog.dismiss();
            waitingDialog = null;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
