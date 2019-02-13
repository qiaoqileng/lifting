package com.qql.mylib.mvp.module.network;

/**
 * @author CharLiu
 */
@SuppressWarnings("serial")
public class HError extends Exception {
    /**
     * 网络异常
     */
    public static final String CONNECT_TIME_OUT = "-998";
    public static final String SO_TIME_OUT = "-999";
    public static final String CONNECT_ERROR = "-1000";
    public static final String NETWORK_ERROR = "-1001";
    public static final String SERVER_STATE_ERROR = "-1002";
    public static final String PARSE_ERROR = "-1003";
    public static final String UNKNOWN_ERROR = "-1004";
    public static final String OUT_OF_LOGIN = "-1005";
    protected String errorMsg;
    protected String errorCode;
    protected int httpStatusCode = 200;

    public HError(Throwable t, String code, String msg) {
        super(t);
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public HError(Throwable t, String code) {
        super(t);
        this.errorCode = code;
        this.errorMsg = "";
    }

    public HError(String code) {
        this.errorCode = code;
        this.errorMsg = "";
    }

    public HError(String code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean isNetworkError() {
        return CONNECT_TIME_OUT.equals(errorCode)
                || SO_TIME_OUT.equals(errorCode)
                || UNKNOWN_ERROR.equals(errorCode)
                || NETWORK_ERROR.equals(errorCode)
                || CONNECT_ERROR.equals(errorCode)
                || SERVER_STATE_ERROR.equals(errorCode);
    }

    public boolean isCustomError() {
        return !(isNetworkError() || errorCode == PARSE_ERROR);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}