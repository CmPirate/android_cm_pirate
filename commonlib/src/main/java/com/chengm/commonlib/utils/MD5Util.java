package com.chengm.commonlib.utils;

import java.security.MessageDigest;

/**
 * program: CmPirate
 * description: MD5加密的简单实现
 * author: ChengMo
 * create: 2019-12-01 10:26
 **/
public class MD5Util {

    /**
     * MD utf-8 编码
     */
    public static String MD5EncodeUtf8(String origin) {
        return MD5Encode(origin, "utf-8");
    }

    /**
     * MD utf-8 编码
     */
    public static String getMD5DefaultPwd() {
        return MD5Encode("999999", "utf-8");
    }

    // 这里主要是遍历8个byte，转化为16位进制的字符，即0-F
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte bt : b)
            resultSb.append(byteToHexString(bt));

        return resultSb.toString();
    }

    // 这里是针对单个byte，256的byte通过16拆分为d1和d2
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5
     */
    private static String MD5Encode(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return StringUtil.getNotNullStr(resultString).toUpperCase();
    }

    private static final String[] hexDigits =
            {
                    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
            };
}
