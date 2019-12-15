package com.chengm.pirate;

import android.os.Bundle;

import com.chengm.commonlib.http.user.User;
import com.chengm.commonlib.http.user.UserRequest;
import com.chengm.commonlib.utils.MD5Util;
import com.chengm.http.LoadingObserver;
import com.chengm.http.utils.L;
import com.trello.rxlifecycle2.components.RxActivity;

/**
 * author : ChenWJ
 * date : 2019/12/7 10:43
 * description :
 */
public class MainActivity extends RxActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        // 网络测试
        int identityType = 1;
        String identifier = "18179739967";
        String pwd = MD5Util.MD5EncodeUtf8("999999");
        UserRequest.getInstance(this).authLogin(identityType, identifier, pwd, new LoadingObserver<User>(this) {
            @Override
            protected void onSuccess(User result) {
                L.d("XXX", result.toString());
            }

            @Override
            protected void onFailure(Throwable e, String errorMsg) {
                L.d("XXX", errorMsg);
            }
        });
    }
}
