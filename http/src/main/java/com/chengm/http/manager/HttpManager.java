package com.chengm.http.manager;

import android.content.Context;
import android.text.TextUtils;

import com.chengm.http.utils.DeviceUtils;
import com.chengm.http.utils.L;
import com.chengm.http.utils.PackageUtils;

/**
 * author : ChenWJ
 * date : 2019/12/15 19:29
 * description : 设备信息管理
 */
public class HttpManager {

    private static HttpManager mInstance;

    private Context mContext;

    // 设备信息
    private String vendor;// 手机厂商
    private String osName;// ANDROID
    private String osVersion;// 系统版本号
    private String deviceName;// 设备型号，如:iphone6s、u880、u8800
    private String deviceId;
    private String clientVersion;// 客户端版本号
    private String token;// token 用户登录成功之后本地保存

    // 是否开启打印日志
    private boolean isEnableLog;

    private HttpManager() {

    }

    public static HttpManager getInstance() {
        if (mInstance == null) {
            mInstance = new HttpManager();
        }
        return mInstance;
    }

    /**
     * 初始化设备信息
     */
    public void init(Context context, String token, boolean isEnableLog) {
        if (context == null) {
            return;
        }
        this.mContext = context;
        this.isEnableLog = isEnableLog;
        vendor = DeviceUtils.getManufacturer();
        osName = "ANDROID";
        osVersion = DeviceUtils.getReleaseVersion();
        deviceName = DeviceUtils.getDeviceModel();
        deviceId = DeviceUtils.getDeviceId(mContext);
        clientVersion = PackageUtils.getVersionName(context);
        this.token = token;
        L.d("TAG", "DEVICE INFO  -> " + "clientVersion:" + clientVersion +
                ", 厂商:" + vendor + ", osName:" + osName + ", 系统版本:" + osVersion + ", 设备型号:" + deviceName + ", IMEI:" + deviceId);
    }

    /**
     * 获取设备厂商
     */
    public String getVendor() {
        if (TextUtils.isEmpty(vendor)) {
            vendor = DeviceUtils.getManufacturer();
        }
        return TextUtils.isEmpty(vendor) ? "" : vendor;
    }

    /**
     * 获取设备号
     */
    public String getOsName() {
        return TextUtils.isEmpty(osName) ? "ANDROID" : osName;
    }

    /**
     * 获取系统版本号
     */
    public String getOsVersion() {
        if (TextUtils.isEmpty(osVersion)) {
            osVersion = DeviceUtils.getReleaseVersion();
        }
        return TextUtils.isEmpty(osVersion) ? "" : osVersion;
    }

    /**
     * 获取设备型号
     */
    public String getDeviceName() {
        if (TextUtils.isEmpty(deviceName)) {
            deviceName = DeviceUtils.getDeviceModel();
        }
        return TextUtils.isEmpty(deviceName) ? "" : deviceName;
    }

    /**
     * 获取设备deviceId
     */
    public String getDeviceId() {
        if (TextUtils.isEmpty(deviceId)) {
            if (mContext != null) {
                deviceId = DeviceUtils.getDeviceId(mContext);
            }
        }
        return TextUtils.isEmpty(deviceId) ? "" : deviceId;
    }

    /**
     * 获取客户端版本号
     */
    public String getClientVersion() {
        if (TextUtils.isEmpty(clientVersion)) {
            if (mContext != null) {
                clientVersion = PackageUtils.getVersionName(mContext);
            }
        }
        return  (TextUtils.isEmpty(clientVersion)) ? "1.0" : clientVersion;
    }

    /**
     * 设置token, 用户登录成功获取token之后需要设置
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 清除token，用户推出登录之后需要清除此参数
     */
    public void clearToken() {
        token = "";
    }

    /**
     * 获取 token
     */
    public String getToken() {
        return TextUtils.isEmpty(token) ? "1" : token;
    }

    /**
     * isEnableLog
     */
    public boolean isEnableLog() {
        return isEnableLog;
    }

}
