package com.chengm.commonlib.utils;

/**
 * program: CmPirate
 * description: String 相关操作
 * author: ChengMo
 * create: 2019-12-01 10:40
 **/
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 判断空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.length() <= 0 || str.trim().isEmpty();
    }

    /**
     * 获取不为空的字符串
     */
    public static String getNotNullStr(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    /**
     * 获取字符串长度
     */
    public static int getLength(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        return str.length();
    }

    /**
     * 把一些html的字符串还原
     *
     * @param source 需要进行处理的字符串
     * @return 处理后的字符串
     */
    public static String decodeHtml(String source) {
        if (source == null) {
            return null;
        }

        String html = new String(source);

        html = replace(html, "&amp;", "&");
        html = replace(html, "&lt;", "<");
        html = replace(html, "&gt;", ">");
        html = replace(html, "&quot;", "\"");
        html = replace(html, " ", "&nbsp;");

        html = replace(html, "\r\n", "\n");
        html = replace(html, "\n", "<br>\n");
        html = replace(html, "\t", "    ");
        html = replace(html, "  ", " &nbsp;");

        return html;
    }

    /**
     * 字符串替换处理，把旧的字符串替换为新的字符串，主要是通过字符串查找进行处理
     *
     * @param source  需要进行替换的字符串
     * @param old     需要进行替换的字符串
     * @param replace 替换成的字符串
     * @return 替换处理后的字符串
     */
    public static String replace(String source, String old, String replace) {
        StringBuffer output = new StringBuffer();

        int sourceLen = source.length();
        int oldLen = old.length();

        int posStart = 0;
        int pos;

        // 通过截取字符串的方式，替换字符串
        while ((pos = source.indexOf(old, posStart)) >= 0) {
            output.append(source.substring(posStart, pos));

            output.append(replace);
            posStart = pos + oldLen;
        }

        // 如果还有没有处理的字符串，则都添加到新字符串后面
        if (posStart < sourceLen) {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }

    /**
     * 两个字符串比较
     */
    public static boolean equals(String equ1, String equ2) {
        return StringUtil.getNotNullStr(equ1).equals(StringUtil.getNotNullStr(equ2));
    }

}
