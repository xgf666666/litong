package com.weibiaogan.litong.common;

import android.arch.lifecycle.LiveData;

import com.weibiaogan.litong.entity.ImageBean;
import com.weibiaogan.litong.entity.LoginBean;
import com.weibiaogan.litong.entity.UserCenterBean;
import com.xx.baseutilslibrary.network.entity.ApiResponse;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AppService {
    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("login/reg")
    Observable<BaseResponseEntity<Object>> register(@Field("phone") String mobile,
                                                    @Field("pwd") String pwd,
                                                    @Field("code") String code,
                                                    @Field("yzm") String yzm);

    /**
     * 图片上传
     *
     * @param mobile string	是	base64
     * @return
     */
    @FormUrlEncoded
    @POST("Index/imgup")
    Observable<BaseResponseEntity<ImageBean>> imgup(@Field("img") String mobile);


    /**
     * 账号登录
     *
     * @param sign
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("login/login")
    Observable<BaseResponseEntity<LoginBean>> login(@Field("sign") String sign,
                                                    @Field("phone") String phone);

    /**
     * 用户更改支付密码
     *
     * @param userId
     * @param token
     * @param code
     * @param balancePayment
     * @return
     */
    @FormUrlEncoded
    @POST("User/update_balance_payment")
    Observable<BaseResponseEntity<Object>> updateBalancePayment(
            @Field("userId") String userId, @Field("token") String token,
            @Field("code") String code,
            @Field("balance_payment") String balancePayment);






    /**
     * 需求方发布项目
     *
     * @param userId       string	是
     * @param token        string	是
     * @param pt_name      string	是	名称
     * @param pt_describe  string	是	描述
     * @param end_time     string	是	项目完结时间，时间戳
     * @param pt_address   string	是	项目地址
     * @param area_id      string	是	项目类型
     * @param first_price  string	是	首款比例
     * @param second_price string	是	二期款比例
     * @param three_price  string	是	三期款比例
     * @param pt_imgs      string	是	图片路径，有逗号分割
     * @param lat_long     string	是	经纬度，逗号隔开
     * @param all_price    string	是	总价
     * @return
     */
    @FormUrlEncoded
    @POST("Publishproject/add_project")
    Observable<BaseResponseEntity<List<Object>>> addProject(@Field("userId") String userId,
                                                            @Field("token") String token,
                                                            @Field("pt_name") String pt_name,
                                                            @Field("pt_describe") String pt_describe,
                                                            @Field("end_time") String end_time,
                                                            @Field("pt_address") String pt_address,
                                                            @Field("area_id") String area_id,
                                                            @Field("first_price") String first_price,
                                                            @Field("second_price") String second_price,
                                                            @Field("three_price") String three_price,
                                                            @Field("pt_imgs") String pt_imgs,
                                                            @Field("lat_long") String lat_long,
                                                            @Field("all_price") String all_price
                                                            );


    /**
     * 用户中心
     *
     * @param userId
     * @param token
     * @return
     */
    @POST("User/index")
    Observable<BaseResponseEntity<UserCenterBean>> UserIndex(@Header("userId") String userId, @Header("token") String token);


    /**
     * 用户中心
     *
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("User/index")
    LiveData<ApiResponse<BaseResponseEntity<UserCenterBean>>> UserIndex2(@Field("userId") String userId, @Field("token") String token);




    /**
     * 修改用户昵称或者修改性别或修改用户头像
     *
     * @param userId
     * @param token  nickname	string	是
     *               user_sex	string	是
     *               user_img	string	是
     * @return
     */
    @FormUrlEncoded
    @POST("User/update_user")
    Observable<BaseResponseEntity<Object>> updateUser(
            @Field("userId") String userId, @Field("token") String token,
            @FieldMap Map<String, String> map);


    /**
     * 忘记密码
     *
     * @param userId
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("User/forget_user_pwd")
    Observable<BaseResponseEntity<Object>> forgetUserPwd(
            @Field("userId") String userId,
            @Field("token") String token,
            @Field("code") String code,
            @Field("new_user_pwd") String newUserPwd);



    /**
     * 账单查询
     *
     * @param page
     */
    @FormUrlEncoded
    @POST("bill_record")
    Observable<BaseResponseEntity<List<Object>>> list(@Field("page") int page);



    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @FormUrlEncoded
    @POST("login/sendSMS")
    Observable<BaseResponseEntity<Object>> sendCode(@Field("phone") String phone);


    /**
     * 修改绑定手机之前验证用户
     *
     * @param userId string	是
     * @param token  string	是
     * @param code   string	是	旧手机验证码 与密码二选一
     * @return
     */
    @FormUrlEncoded
    @POST("User/update_before_user_phone")
    Observable<BaseResponseEntity<Object>> updateBeforeUserPhone(@Field("userId") String userId,
                                                                 @Field("token") String token,
                                                                 @Field("code") String code
    );


    /**
     * 修改绑定手机输入新手机号与验证码
     *
     * @param userId       string	是
     * @param token        string	是
     * @param newUserPhone string	否	新手机号
     * @param code         string	是	旧手机验证码 与密码二选一
     * @return
     */
    @FormUrlEncoded
    @POST("User/update_user_phone")
    Observable<BaseResponseEntity<Object>> updateUserPhone(@Field("userId") String userId,
                                                           @Field("token") String token,
                                                           @Field("new_user_phone") String newUserPhone,
                                                           @Field("code") String code
    );


    /**
     * 忘记密码
     *
     * @param user_phone
     * @param code
     * @param new_user_pwd
     * @return
     */
    @FormUrlEncoded
    @POST("login/update_user_pwd")
    Observable<BaseResponseEntity<Object>> forgetPwd(@Field("user_phone") String user_phone
            , @Field("code") String code
            , @Field("new_user_pwd") String new_user_pwd);


}
