package com.chengm.pirate;

import android.app.Application;
import android.content.Context;

import com.chengm.commonlib.utils.SPUtils;
import com.chengm.http.manager.HttpManager;
import com.chengm.pirate.constant.SPKey;

/**
 * author : ChenWJ
 * date : 2019/11/9 16:32
 * description :
 */
public class MyApp extends Application {

    private static MyApp mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 初始化
        init();
    }

    // 打开app时初始化数据
    private void init() {
        // 初始化设备信息
        String token = SPUtils.getStrValue(mContext, SPKey.TOKEN);
        HttpManager.getInstance().init(mContext, token, true);
    }

    public static Context getContext() {
        return mContext;
    }
}
