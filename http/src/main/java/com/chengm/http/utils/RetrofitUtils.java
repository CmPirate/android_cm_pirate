package com.chengm.http.utils;

import com.chengm.http.constant.RxConfig;
import com.chengm.http.interceptor.ConfigInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : ChenWJ
 * date : 2019/12/15 16:24
 * description : Retrofit封装
 */
public class RetrofitUtils {

    private static RetrofitUtils mInstance;

    /**
     * 单例模式
     */
    public static <T> T createApi(Class<T> service) {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance.getRetrofit(service);
    }

    private RetrofitUtils() {
    }

    private <T> T getRetrofit(Class<T> service) {
        // 初始化Retrofit
        return initRetrofit(initOkHttp()).create(service);
    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(RxConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 初始化okhttp
     */
    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(RxConfig.DEFAULT_TIME, TimeUnit.SECONDS)// 设置读取超时时间
                .connectTimeout(RxConfig.DEFAULT_TIME, TimeUnit.SECONDS)// 设置请求超时时间
                .writeTimeout(RxConfig.DEFAULT_TIME, TimeUnit.SECONDS)// 设置写入超时时间
                .addInterceptor(new ConfigInterceptor())// 添加打印拦截器
                .retryOnConnectionFailure(true)// 设置出现错误进行重新连接。
                .build();
    }

}
