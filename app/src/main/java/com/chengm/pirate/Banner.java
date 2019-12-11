package com.chengm.pirate;

import java.io.Serializable;

/**
 * author : ChenWJ
 * date : 2019/11/9 11:38
 * description :
 */
public class Banner implements Serializable {

    private String url;

    private int resId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
