package com.chengm.pirate;

import android.app.Application;
import android.content.Context;

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
    }

    public static Context getContext() {
        return mContext;
    }
}
