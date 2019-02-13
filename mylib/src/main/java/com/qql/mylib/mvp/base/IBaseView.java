package com.qql.mylib.mvp.base;

import android.content.DialogInterface;

/**
 * Created by yxf on 2017/8/4.
 */

public interface IBaseView {
    void showWaiting();

    void hideWaiting();

    /**
     * @param cancelable BaseViewImpl默认true
     * @param onCancelListener 进度条小时候的回调
     */
    void showWaiting(boolean cancelable, DialogInterface.OnCancelListener onCancelListener);

    void handleError(Exception error);

    /**
     * 需要登陆状态的接口，跳到登陆页面
     */
    void toLogin();
}
