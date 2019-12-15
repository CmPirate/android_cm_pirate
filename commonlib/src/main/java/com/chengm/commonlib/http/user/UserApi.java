package com.chengm.commonlib.http.user;

import com.chengm.http.base.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author : ChenWJ
 * date : 2019/12/15 16:54
 * description : 用户
 */
public interface UserApi {

    @POST("user/accountLogin")
    @FormUrlEncoded
    Observable<BaseResponse<User>> authLogin(@FieldMap Map<String, Object> map);

}
