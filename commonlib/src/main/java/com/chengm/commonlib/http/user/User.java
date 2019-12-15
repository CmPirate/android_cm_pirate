package com.chengm.commonlib.http.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author : ChenWJ
 * date : 2019/12/15 17:32
 * description : 用户相关数据
 */
@Setter
@Getter
@ToString
public class User {

    private long uid;

    private String userName;

    private String face;

    private String mobile;

    private String email;

    private int gender;

    private String city;

    private String birthday;

    private String signature;

}
