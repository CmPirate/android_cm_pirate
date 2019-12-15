package com.chengm.http.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * author : ChenWJ
 * date : 2019/12/15 19:06
 * description : 获取设备信息
 */
public class DeviceUtils {

    /**
     * 获取系统Android版本
     */
    public static int getSDKVersionInt() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获得设备的固件版本号
     */
    public static String getReleaseVersion() {
        return (Build.VERSION.RELEASE == null) ? "v1" : Build.VERSION.RELEASE;
    }

    /**
     * 检测当前设备是否是特定的设备
     *
     * @param devices 设备列表
     * @return 是否是特定的设备
     */
    public static boolean isDevice(String... devices) {
        String model = DeviceUtils.getDeviceModel();
        if (devices != null && model != null) {
            for (String device : devices) {
                if (model.contains(device)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获得设备型号
     */
    public static String getDeviceModel() {
        return (Build.MODEL) == null ? "" : (Build.MODEL).trim();
    }

    /**
     * 获取厂商信息
     */
    public static String getManufacturer() {
        return (Build.MANUFACTURER) == null ? "" : (Build.MANUFACTURER).trim();
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context ctx) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 获取CPU的信息
     */
    public static String getCpuInfo() {
        String cpuInfo = "";
        try {
            if (new File("/proc/cpuinfo").exists()) {
                FileReader fr = new FileReader("/proc/cpuinfo");
                BufferedReader localBufferedReader = new BufferedReader(fr,
                        8192);
                cpuInfo = localBufferedReader.readLine();
                localBufferedReader.close();
                if (cpuInfo != null) {
                    cpuInfo = cpuInfo.split(":")[1].trim().split(" ")[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuInfo;
    }

    /**
     * 判断是否支持闪光灯
     */
    public static boolean isSupportCameraLedFlash(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm != null) {
            FeatureInfo[] features = pm.getSystemAvailableFeatures();
            if (features != null) {
                for (FeatureInfo f : features) {
                    if (f != null
                            && PackageManager.FEATURE_CAMERA_FLASH
                            .equals(f.name)) // 判断设备是否支持闪光灯
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * 检测设备是否支持相机
     */
    public static boolean isSupportCameraHardware(Context context) {
        if (context != null
                && context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

}
