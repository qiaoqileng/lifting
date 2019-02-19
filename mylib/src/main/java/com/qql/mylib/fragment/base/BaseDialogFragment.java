package com.qql.mylib.fragment.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.qql.mylib.BaseApp;
import com.qql.mylib.mvp.base.IBasePresenter;
import com.qql.mylib.mvp.base.IBaseView;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * author xupj
 * e-mail xupingjie@hztianque.com
 * time   2018/10/18
 * desc  mvp v层基类  本身实现懒加载逻辑
 */
public abstract class BaseDialogFragment<V,P extends IBasePresenter<V>> extends DialogFragment implements IBaseView {
    protected P mPresenter;
    private Toast mToast;
    private Unbinder mUnbinder;
    protected KProgressHUD waitingDialog;
    // 懒加载标志位
    private boolean isViewCreated = false;  // onCreateView()方法已经执行
    private boolean isFirstLoad = true;       // 已经被加载过
    private boolean isVisibleToUser = false;// Fragment UI正要呈现给用户

    // 该方法执行时机可能在 onCreateView方法之前调用 ，此时调用loadData可能会导致空指针问题
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        useLazyLoader();
        if (!isVisibleToUser) {
            // 当fragment 隐藏是不再弹出toast
            cancelToast();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window win = getDialog().getWindow();
        if (win == null || getActivity() == null) {
            return;
        }
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = dm.heightPixels/2;
        win.setAttributes(params);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // viewpager + fragment 会导致onHiddenChanged不再执行
        super.onHiddenChanged(hidden);

    }

    // 判断是否使用懒加载方式 加载数据 ，子类加载数据使用loadData方法
    private void useLazyLoader() {
        // 使用懒加载条件
        // 1. 需要使用懒加载
        // 2. ui可见，
        // 3. onCreateView方法执行，
        // 4. 没有被加载过，
        // 5. 且父fragment是可见状态(解决多层fragment可见状态)
        if (useLazyLoad() && isVisibleToUser && isViewCreated && isFirstLoad /*&& isParentFragmentVisible()*/) {
            // 此时loadData只会再第一次加载数据是执行一次
            loadData();
            isFirstLoad = false;
        }
    }

    // 多层fragment嵌套时可能会导致懒加载失效
//    private boolean isParentFragmentVisible() {
//        return getParentFragment() == null ||  getParentFragment().getUserVisibleHint();
//    }

    /**
     * 是否使用懒加载
     *
     * @return true 使用懒加载 false不使用懒加载
     */
    protected boolean useLazyLoad() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewId() == 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(getContentViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            //noinspection unchecked
            mPresenter.attach((V) this);
        }

        // 避免onCreateView方法还未执行就加载数据导致空指针问题
        isViewCreated = true;
        initParams(savedInstanceState);
        if (!useLazyLoad()) {
            // 如果不使用懒加载再onViewCreated 的时候直接加载数据
            loadData();
            // 数据已经加载过
            isFirstLoad = false;
        } else {
            // 使用懒加载只在当前fragment正要与用户交互的时候才去加载数据
            useLazyLoader();
        }

        Window win = getDialog().getWindow();
        if (win == null || getActivity() == null) {
            return;
        }
        win.requestFeature(Window.FEATURE_NO_TITLE);
//        // 一定要设置Background，如果不设置，window属性设置无效
//        win.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
//
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        WindowManager.LayoutParams params = win.getAttributes();
//        params.gravity = Gravity.BOTTOM;
//        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = dm.heightPixels/2;
//        win.setAttributes(params);
    }

    /**
     * 该方法先于 {@link this#loadData()} 方法执行做一些初始化工作
     *
     * @param savedInstanceState save状态
     */
    protected void initParams(Bundle savedInstanceState) {

    }


    /**
     * 使用懒加载是只有再与用户交互的时候才会加载一次数据
     * 否则再onViewCreated方法执行后直接执行
     * {@link this#useLazyLoad()}
     */
    protected void loadData() {
        // 加载数据
    }

    /**
     * 布局文件
     *
     * @return 布局文件
     */
    protected abstract int getContentViewId();

    @SuppressLint("ShowToast")
    public void toast(String msg) {
        if (isHidden()) {
            // 如果当前界面以隐藏不再弹出toast 提示
            return;
        }
        if (!TextUtils.isEmpty(msg)) {
            if (mToast == null && getActivity() != null) {
                mToast = Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT);
            }
            // 如果getActivity() = null 会导致 mToast = null
            if (mToast != null) {
                mToast.setText(msg);
                mToast.show();
            }
        }
    }

    @SuppressWarnings("unused")
    protected void toast(@StringRes int msgId) {
        toast(getResources().getString(msgId));
    }


    protected void cancelToast() {
        if (mToast != null) {
            // 避免Fragment 不可见时 仍然弹出toast提示影响用户体验
            mToast.cancel();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    /**
     * @return mvp presenter层实例
     */
    protected abstract P getPresenter();

    @Override
    public void toLogin() {
        BaseApp.getApplication().toLogin();
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
        if (isDetached() || getActivity() == null){
            return;
        }
        getActivity().runOnUiThread(() -> {
            if (waitingDialog == null || !waitingDialog.isShowing()) {
                if (!isDetached()) {
                    waitingDialog = KProgressHUD.create(getActivity())
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
}
