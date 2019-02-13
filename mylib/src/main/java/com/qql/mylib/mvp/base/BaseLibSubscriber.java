package com.qql.mylib.mvp.base;

import com.qql.mylib.BuildConfig;
import com.qql.mylib.mvp.module.network.HError;
import com.qql.mylib.mvp.module.response.BaseJsonResponse;
import com.qql.mylib.mvp.module.response.BaseResponse;

import java.net.SocketTimeoutException;

import io.reactivex.subscribers.DisposableSubscriber;

import static com.qql.mylib.mvp.module.network.HError.OUT_OF_LOGIN;

/**
 * Created by yxf on 2018/7/17.
 */

public abstract class BaseLibSubscriber<T> extends DisposableSubscriber<BaseResponse<T>> {
    private IBaseView mView;
    private boolean showDialog;
    protected BaseLibSubscriber(IBaseView baseView) {
        this.mView = baseView;
    }

    public BaseLibSubscriber(IBaseView baseView, boolean showDialog) {
        this.mView = baseView;
        this.showDialog = showDialog;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mView != null && showDialog) {
            mView.showWaiting();
        }
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        if (baseResponse.isSuccess()) {
            onSuccess(baseResponse.getModule());
        } else {
            if (BaseJsonResponse.ERRCODE_LOGIN.equals(baseResponse.getErrorCode())) {
                if (needSession() && isLogin()) {
                    onSessionExpired();
                }
            } else {
                onFailure(new HError(OUT_OF_LOGIN,baseResponse.getErrorMessage()));
            }
        }
    }

    /**
     * 是否需要登陆状态，默认不需要，需要记得重写
     */
    protected abstract boolean needSession();

    protected abstract boolean isLogin();

    private void onSessionExpired(){
        if (mView != null) {
            mView.toLogin();
        }
    }

    @Override
    public void onError(Throwable t) {

        if (t instanceof SocketTimeoutException) {
            onFailure(new HError(HError.SO_TIME_OUT, getErrorMsg(t)));
        } else {
            onFailure(new HError(HError.CONNECT_ERROR, getErrorMsg(t)));
        }
        if (mView != null && showDialog) {
            mView.hideWaiting();
        }
        if (BuildConfig.DEBUG) {
            t.printStackTrace();
        }
    }

    protected String getErrorMsg(Throwable t) {
        if (t instanceof SocketTimeoutException) {
            return "请求超时,请稍后重试";
        } else {
            return "当前网络不通畅，请稍后再试";
        }
    }


    @Override
    public void onComplete() {
        if (mView != null && showDialog) {
            mView.hideWaiting();
        }
    }

    protected abstract void onSuccess(T t);

    protected void onFailure(HError error){
        if (mView != null) {
            mView.handleError(error);
        }
    }
}
