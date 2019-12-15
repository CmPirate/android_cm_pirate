package com.chengm.http.base;

import com.chengm.http.constant.Code;
import com.chengm.http.utils.RxExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author : ChenWJ
 * date : 2019/12/15 15:48
 * description : 数据返回统一处理
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    @Override
    public void onNext(BaseResponse<T> response) {
        // 基础数据统一处理
        if (response.getCode() == Code.SUCCESS) {
            onSuccess(response.getData());
        } else {
            onFailure(null, response.getMsg());
        }
    }

    /**
     * 服务器错误信息处理
     */
    @Override
    public void onError(Throwable e) {
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    /**
     * 请求成功
     */
    protected abstract void onSuccess(T result);

    /**
     * 请求失败
     */
    protected abstract void onFailure(Throwable e, String errorMsg);

}
