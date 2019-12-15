package com.chengm.http.constant;

import androidx.annotation.IntDef;

/**
 * author : ChenWJ
 * date : 2019/12/15 15:51
 * description : 响应码
 */
@IntDef({Code.SUCCESS})
public @interface Code {

    /**
     * success
     */
    int SUCCESS = 200;

}
