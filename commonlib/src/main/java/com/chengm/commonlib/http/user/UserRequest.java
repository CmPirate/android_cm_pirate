package com.chengm.commonlib.http.user;

import com.chengm.http.LoadingObserver;
import com.chengm.http.utils.RetrofitUtils;
import com.chengm.http.manager.RxHelper;
import com.trello.rxlifecycle2.components.RxActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * author : ChenWJ
 * date : 2019/12/15 17:00
 * description :
 */
public class UserRequest {

    private static UserRequest mInstance;

    private UserApi mApi;
    private RxActivity mContext;

    private UserRequest(RxActivity context) {
        this.mContext = context;
        mApi = RetrofitUtils.createApi(UserApi.class);
    }

    public static UserRequest getInstance(RxActivity context) {
        if (mInstance == null) {
            synchronized (UserRequest.class) {
                if (mInstance == null) {
                    mInstance = new UserRequest(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 用户账号密码登录
     *
     * @param identityType 1 手机号  4 邮箱
     * @param identifier   根据identityType传参
     */
    public void authLogin(int identityType, String identifier, String password, LoadingObserver<User> observer) {
        Map<String, Object> param = new HashMap<>();
        param.put("identityType", identityType);
        param.put("identifier", identifier);
        param.put("password", password);
        mApi.authLogin(param)
                .compose(RxHelper.observableIO2Main(mContext))
                .subscribe(observer);
    }

}
