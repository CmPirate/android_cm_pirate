package com.chengm.commonlib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author : ChenWJ
 * date : 2019/12/15 21:05
 * description : SharedPreferences
 */
public class SPUtils {

    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "cm_share_date";

    public static int getIntVaue(Context context, String key) {
        return (int) getParam(context, key, 0);
    }

    public static String getStrValue(Context context, String key) {
        return (String) getParam(context, key, "");
    }

    public static boolean getBooleanValue(Context context, String key) {
        return (boolean) getParam(context, key, false);
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     */
    public static void setParam(Context context, String key, Object object) {
        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(type, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(type, (Long) object);
        }
        editor.commit();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object getParam(Context context, String key, Object defaultObject) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(type, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(type, (Long) defaultObject);
        }
        return null;
    }

}
