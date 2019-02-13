package com.qql.mylib.mvp.module.response;


import com.qql.mylib.BaseApp;
import com.qql.mylib.R;

/**
 * Created by charliu on 6/16/16.
 */
public class BaseJsonResponse<T> implements BaseResponse<T> {
    public final static String ERRCODE_LOGIN = "IOE100-01";//sid失效
    public boolean success;
    public String errCode;
    public String errMessage;
    private T module;

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        if (success) {
            return "";
        } else if (BaseJsonResponse.ERRCODE_LOGIN.equals(errCode)) {
            return BaseApp.getApplication().getString(R.string.errcode_session_expired);
        } else {
            return errMessage;
        }
    }

    public String getErrorCode() {
        if (success) {
            return "";
        } else {
            return errCode;
        }
    }

    @Override
    public String toString() {
        return "BaseJsonResponse{" +
                "errCode=" + errCode +
                ", errMessage='" + errMessage + '\'' +
                '}';
    }
}
