package com.weibiaogan.litong.common;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;


import com.weibiaogan.litong.entity.LoginBean;
import com.weibiaogan.litong.entity.UserCenterBean;
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager;

/**
 * Constants
 * 沉迷学习不能自拔
 * Describe：
 * Created by 雷小星🍀 on 2018/2/26 10:55.
 */

public class Constants {
    private static final String TAG = "Constants";
    public static final String KEY_SP_TOKEN = "SP_TOKEN";
    public static final int INTENT_HOME = 0;
    public static final int INTENT_SHOPPING = 2;
    public static final String KEY_INTENT_MAIN = "INTENT_MAIN";
    public static final String KEY_INTENT_VIP = "VipID";//Vip详情页传递字段
    public static final String KEY_INTENT_MSG_ID = "msg_id";//消息id
    public static final String KEY_DATA = "data";//Intent数据传输字段
    private static final String KEY_SP_USER = "SP_USER";
    private static final String KEY_SP_LOCATION = "SP_LOCATION";//定位信息
    private static final String KEY_SP_LOGIN = "SP_IS_LOGIN";//是否登录
    private static final String KEY_SP_IS_FIRST = "SP_IS_FIRST";//是否第一次使用当前版本App
    private static final String KEY_SP_HOME = "SP_HOME";//首页数据
    private static final String KEY_SP_IS_FIRST_LOGIN = "SP_IS_FIRST_LOGIN";//是否第一登录
    private static final String KEY_USER_PHONE="SP_USER_PHONE";//手机号
    //滑动关闭Activity
    public static final int ACTIVITY_FINISH_REQUEST_CODE = 10000;
    public static final int ACTIVITY_FINISH_RESULT_CODE = 10001;

    //接口Code
    public static final String LONG_TOKEN_INVALID = "30001";
    public static final String LONG_TOKEN_INVALID_ = "30002";
    public static final String SHORT_TOKEN_INVALID = "30003";
    public static final String SHORT_TOKEN_INVALID_ = "30004";
    private static UserCenterBean userData;







    public static UserCenterBean getUserData() {
        return userData;
    }

    public static void setUserData(UserCenterBean userData) {
        Constants.userData = userData;
    }




    /**
     * 用户是否登录
     */
    public static boolean isLogin() {
        return SPUtils.getInstance().getBoolean(KEY_SP_LOGIN);
    }

    /**
     * 登录
     */
    public static void login() {
        SPUtils.getInstance().put(KEY_SP_LOGIN, true);
    }

    /**
     * 登出
     */
    public static void loginOut() {
        SPUtils.getInstance().put(KEY_SP_LOGIN, false);
    }


    /**
     * 是否是第一次使用
     */
    public static boolean isFirst() {
        return SPUtils.getInstance().getBoolean(KEY_SP_IS_FIRST, true);
    }


    /**
     * 是否有支付密码
     *
     * @return true 有支付密码  要改密码 需要输入旧密码
     * <p>
     * false 没有支付密码    首次设置传验证码
     */
    public static boolean hasPayPassword() {
        return false;
    }

    /**
     * 设置为非首次使用app
     */
    public static void setNotFirst() {
        SPUtils.getInstance().put(KEY_SP_IS_FIRST, false);
    }

    /**
     * 获取token
     *
     * @return
     */
    public static LoginBean getToken() {
        String loginJson = SPUtils.getInstance().getString(KEY_SP_TOKEN);
        LoginBean loginEntity = new Gson().fromJson(loginJson, LoginBean.class);
        return loginEntity;
    }

    /**
     * 储存token
     *
     * @param loginEntity
     */
    public static void putToken(LoginBean loginEntity) {
        SPUtils.getInstance().put(KEY_SP_TOKEN, new Gson().toJson(loginEntity));
    }

    /**
     * 获取用户手机号
     * @return
     */
    public static String getPhone(){
       return SPUtils.getInstance().getString(KEY_USER_PHONE);
    }

    public static void putPhone(String phone){
        SPUtils.getInstance().put(KEY_USER_PHONE,phone);
    }



    /**
     * 获取是否第一次登录
     *
     * @return
     */
    public static boolean isFirstLogin() {
        return SPUtils.getInstance().getBoolean(KEY_SP_IS_FIRST_LOGIN, true);
    }

    /**
     * 储存是否第一次登录
     *
     * @param isFirstLogin
     */
    public static void setIsFirstLogin(boolean isFirstLogin) {
        SPUtils.getInstance().put(KEY_SP_IS_FIRST_LOGIN, isFirstLogin);
    }


    /**
     * 获取Host
     *
     * @return
     */
    public static String getBaseUrl() {
        return Retrofit2Manager.Companion.getInstance().getApiConfigProvider().getDebugHost();
    }

    /**
     * 存入经纬信息
     */
    public static void putLocation(double latitude, double longitude, String city) {
        SPUtils.getInstance().put(Constants.KEY_SP_LOCATION, latitude + "," + longitude + "," + city);
    }

    /**
     * 获取经纬信息
     *
     * @return
     */
    public static String[] getLocation() {
        return SPUtils.getInstance()
                .getString(Constants.KEY_SP_LOCATION)
                .split(",");
    }
}
