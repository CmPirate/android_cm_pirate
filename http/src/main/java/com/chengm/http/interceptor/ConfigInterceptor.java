package com.chengm.http.interceptor;

import com.chengm.http.manager.HttpManager;
import com.chengm.http.utils.L;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * author : ChenWJ
 * date : 2019/12/15 17:18
 * description : 拦截器
 */
public class ConfigInterceptor implements Interceptor {

    private final String TAG = "okhttp";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // 添加公共参数
        Request.Builder newBuilder = request.newBuilder();
        Request build = newBuilder
                .addHeader("deviceId", HttpManager.getInstance().getDeviceId())
                .addHeader("osName", HttpManager.getInstance().getOsName())
                .addHeader("osVersion", HttpManager.getInstance().getOsVersion())
                .addHeader("clientVersion", HttpManager.getInstance().getClientVersion())
                .addHeader("vendor", HttpManager.getInstance().getVendor())
                .addHeader("deviceName", HttpManager.getInstance().getDeviceName())
                .addHeader("token", HttpManager.getInstance().getToken())
                .build();

        L.i(TAG, "request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(build);
        long t2 = System.nanoTime();
        L.i(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        L.e(TAG, "response body:" + content);
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}
