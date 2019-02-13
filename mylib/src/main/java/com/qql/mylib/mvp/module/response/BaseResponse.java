package com.qql.mylib.mvp.module.response;

/**
 * Created by T-46 on 2016/6/23.
 */
public interface BaseResponse<T> {
    boolean isSuccess();

    String getErrorMessage();

    String getErrorCode();

    T getModule();
}
