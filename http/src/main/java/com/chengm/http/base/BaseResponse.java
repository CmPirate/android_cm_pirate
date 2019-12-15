package com.chengm.http.base;

/**
 * author : ChenWJ
 * date : 2019/12/15 15:34
 * description : 接口数据统一响应
 */
public class BaseResponse<T> {

    /**
     * 响应码 200：success  400：fail
     */
    private int code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
