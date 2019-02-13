package com.qql.lifting.mvp.module.network;

import android.util.Log;

import com.qql.mylib.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final String JSON_REQUEST_CONTENT_TYPE = "application/json; charset=UTF-8";
    private static final boolean IS_SIGN = true;

    private static final class RetrofitHolder {
        private static final Retrofit RETROFIT_INSTANCE = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.SERER_NAME + "/")
                .client(OkHttpClientHolder.OK_HTTP_CLIENT_INSTANCE)
                .build();
    }

    private static final class OkHttpClientHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT_INSTANCE = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier((hostname, session) -> true)
                .addInterceptor(providHttpLoggingInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor providHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(message -> Log.e("retrofitResponse", message));
        loggerInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggerInterceptor;
    }

    private static Interceptor provideHeaderAndParamsInterceptor() {
        return null;
    }


    /**
     * @return retrofit 单例实例
     */
    @SuppressWarnings("unused")
    public static Retrofit getRetrofit() {
        return RetrofitHolder.RETROFIT_INSTANCE;
    }

    /**
     * 提供单例配置的网络客户端 供给第三方框架是使用
     *
     * @return OkHttpClient 单例
     */
    @SuppressWarnings("unused")
    public static OkHttpClient getOkHttpClient() {
        return OkHttpClientHolder.OK_HTTP_CLIENT_INSTANCE;
    }

    private static final class ApiServiceHolder {
        private static final ApiService CLUE_API_SERVICE_INSTANCE = RetrofitHolder.RETROFIT_INSTANCE
                .create(ApiService.class);
    }

    public static ApiService getApiService() {
        return BuildConfig.DEBUG?new TestApi():ApiServiceHolder.CLUE_API_SERVICE_INSTANCE;
    }
}
