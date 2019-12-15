package com.chengm.http.utils;

import android.util.Log;

import com.chengm.http.manager.HttpManager;

/**
 * author : ChenWJ
 * date : 2019/12/15 20:49
 * description : 日志打印
 */
public class L {

    private static String TAG = "ChengMo";

    public static boolean isEnableLog = HttpManager.getInstance().isEnableLog();

    private L() {

    }

    public static void v(String tag, String log) {
        logger(tag, log, 1);
    }

    public static void v(String log) {
        logger(log, 1);
    }

    public static void d(String tag, String log) {
        logger(tag, log, 2);
    }

    public static void d(String log) {
        logger(log, 2);
    }

    public static void i(String tag, String log) {
        logger(tag, log, 3);
    }

    public static void i(String log) {
        logger(log, 3);
    }

    public static void w(String tag, String log) {
        logger(tag, log, 4);
    }

    public static void w(String log) {
        logger(log, 4);
    }

    public static void e(String tag, String log) {
        logger(tag, log, 5);
    }

    public static void e(String log) {
        logger(log, 5);
    }

    private static void logger(String log, int level) {
        if (!isEnableLog) {
            return;
        }
        logger(TAG, log, level);
    }

    private static void logger(String tag, String log, int level) {
        if (!isEnableLog) {
            return;
        }
        if (level == 1) {
            Log.v(tag, log);
        } else if (level == 2) {
            Log.d(tag, log);
        } else if (level == 3) {
            Log.i(tag, log);
        } else if (level == 4) {
            Log.w(tag, log);
        } else {
            Log.e(tag, log);
        }
    }

}
