package com.fangku.commonlibrary.common;

import android.text.TextUtils;

import com.fangku.commonlibrary.global.CommonGlobal;
import com.fangku.commonlibrary.parse.JsonMananger;
import com.fangku.commonlibrary.utils.SPUtil;

/**
 * 用户信息管理类
 * Create by: chenwei.li
 * Date: 2016-06-21
 * time: 15:29
 * Email: lichenwei.me@foxmail.com
 */
public class UserDataUtil {

    public static final String KEY = "USER";

    /**
     * 获取用户信息实体类
     *
     * @return
     */
    public static UserEntity getUserInfo() {
        String userInfo = (String) SPUtil.get(CommonGlobal.getApplicationContext(), KEY, "");
        if (TextUtils.isEmpty(userInfo.trim())) {
            return new UserEntity();
        } else {
            UserEntity userEntity = JsonMananger.jsonToBean(userInfo, UserEntity.class);
            if (userEntity == null) {
                return new UserEntity();
            }
            return userEntity;
        }
    }


    /**
     * 更新用户信息实体类
     *
     * @param userEntity
     */
    public static void updateUserInfo(UserEntity userEntity) {
        if(userEntity==null){
            return;
        }
        SPUtil.put(CommonGlobal.getApplicationContext(), KEY, JsonMananger.beanToJson(userEntity));
    }

    /**
     * 清除用户信息
     */
    public static void clearUserInfo(){
        SPUtil.clear(CommonGlobal.getApplicationContext());
    }

}
