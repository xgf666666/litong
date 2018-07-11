package com.weibiaogan.litong.common;

import android.arch.lifecycle.LiveData;

import com.weibiaogan.litong.entity.HomeBean;
import com.weibiaogan.litong.entity.ImageBean;
import com.weibiaogan.litong.entity.LoginBean;
import com.weibiaogan.litong.entity.MemberBean;
import com.weibiaogan.litong.entity.MemberpowrBean;
import com.weibiaogan.litong.entity.MyReceiptBean;
import com.weibiaogan.litong.entity.ProjectBean;
import com.weibiaogan.litong.entity.PublicProjectBean;
import com.weibiaogan.litong.entity.SearchProjectBean;
import com.weibiaogan.litong.entity.StoreDetailBean;
import com.weibiaogan.litong.entity.StoreListBean;
import com.weibiaogan.litong.entity.UserCenterBean;
import com.weibiaogan.litong.entity.WorkDetailBean;
import com.weibiaogan.litong.entity.WorkEvaluateBean;
import com.weibiaogan.litong.entity.WorkListBean;
import com.xx.baseutilslibrary.network.entity.ApiResponse;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
     *               user_img	string	是 @FieldMap Map<String, String> map
     * @return
     */
    @FormUrlEncoded
    @POST("User/update_user")
    Observable<BaseResponseEntity<Object>> updateUser(
            @Header("userId") String userId, @Header("token") String token,
            @Field ("user_img") String user_img);

    @FormUrlEncoded
    @POST("User/update_user")
    Observable<BaseResponseEntity<Object>> updateUserSex(
            @Header("userId") String userId, @Header("token") String token,
            @Field ("user_sex") String user_img);
    @FormUrlEncoded
    @POST("User/update_user")
    Observable<BaseResponseEntity<Object>> updateUserName(
            @Header("userId") String userId, @Header("token") String token,
            @Field ("nickname") String user_img);


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
     * 支付宝充值
     *
     * @param userId
     * @param token
     * @param price
     * @param account
     * @param type
     * @param balance_payment
     * @return
     */
    @FormUrlEncoded
    @POST("Withdraw/withdraw_add")
    Observable<BaseResponseEntity<List<Object>>> withdrawAdd(@Field("userId") String userId
            , @Field("token") String token
            , @Field("price") String price
            , @Field("account") String account
            , @Field("type") String type
            , @Field("balance_payment") String balance_payment
    );

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


    /**
     * 首页数据
     *
     * @param page 当前页数
     * @param lat  经度
     * @param lng  纬度
     * @return
     */
    @GET("Index/index")
    Observable<BaseResponseEntity<HomeBean>> homeData(@Query("page") String page , @Query("lat") String lat , @Query("lng") String lng);

    /**
     * 工人列表
     * @param userId
     * @param token
     * @param page
     * @param lat
     * @param lng
     * @return
     */
    @GET("Worker/worker_list")
    Observable<BaseResponseEntity<List<WorkListBean>>> workList(@Header("userId") String userId, @Header("token") String token,
                                                                @Query("page") String page , @Query("lat") String lat , @Query("lng") String lng);

    /**
     * 工人详情
     * @param userId
     * @param token
     * @param user_id
     * @param lat
     * @param lng
     * @return
     */
    @GET("Worker/worker_detail")
    Observable<BaseResponseEntity<WorkDetailBean>> workDetail(@Header("userId") String userId, @Header("token") String token,
                                                              @Query("worker_user_id") String user_id , @Query("lat") String lat , @Query("lng") String lng);

    /**
     * 工人评论
     * @param userId
     * @param token
     * @param user_id
     * @return
     */
    @FormUrlEncoded
    @POST("Worker/worker_comments")
    Observable<BaseResponseEntity<WorkEvaluateBean>> workEvaluate(@Header("userId") String userId, @Header("token") String token,
                                                                        @Field("worker_user_id") String user_id , @Field("page") String page);

    /**
     * 店铺列表
     * @param userId
     * @param token
     * @param page
     * @param lat
     * @param lng
     * @return
     */
    @GET("Store/store_list")
    Observable<BaseResponseEntity<List<StoreListBean>>> storeList(@Header("userId") String userId, @Header("token") String token,
                                                                  @Query("page") String page , @Query("lat") String lat , @Query("lng") String lng , @Query("type") String type);

    /**
     * 店铺详情
     * @param userId
     * @param token
     * @param store_id
     * @param lat
     * @param lng
     * @return
     */
    @GET("Store/store_detail")
    Observable<BaseResponseEntity<StoreDetailBean>> storeDetail(@Header("userId") String userId, @Header("token") String token,
                                                                @Query("store_id") String store_id , @Query("lat") String lat , @Query("lng") String lng);

    /**
     * 项目列表   （我要接单，历史项目）
     * @param userId
     * @param token
     * @param stat 1 可接单 2 已完成
     * @param lat
     * @param lng
     * @param page
     * @param type 1 全部 2 时间
     * @return
     */
    @GET("Publishproject/project_list")
    Observable<BaseResponseEntity<List<ProjectBean>>> historyProject(@Header("userId") String userId, @Header("token") String token,
                                                                     @Query("stat") String stat,@Query("lat") String lat,@Query("lng") String lng,@Query("page") String page,@Query("type") String type);

    /**
     * 搜索项目
     * @param pt_name
     * @param page
     * @param lat
     * @param lng
     * @return
     */
    @GET("Hotkey/pro_search")
    Observable<BaseResponseEntity<List<SearchProjectBean>>> searchProject(@Query("pt_name") String pt_name , @Query("page") String page , @Query("lat") String lat , @Query("lng") String lng);


    /**
        * 会员充值 -会员特权
        * @param userId
        * @param token
        * @return
        */
     @GET("User/user_grade_member")
    Observable<BaseResponseEntity<MemberpowrBean>> vipPwor(@Header("userId") String userId, @Header("token") String token);

    /**
     * 退出登录
     * @param userId
     * @param token
     * @return
     */
    @GET("User/del_user")
    Observable<BaseResponseEntity<Object>> loginOff (@Header("userId") String userId, @Header("token") String token);



    /**
     * 会员充值
     * @param userId
     * @param token
     * @return
     */
    @GET("User/user_grade")
    Observable<BaseResponseEntity<MemberBean>> vip(@Header("userId") String userId, @Header("token") String token);

    /**
     * 我的接单
     * @param userId
     * @param token
     * @param stat 1 可接单 2 已完成
     * @param page
     * @return
     */
    @GET("Publishproject/worker_project_list")
    Observable<BaseResponseEntity<List<MyReceiptBean>>> workProjectList(@Header("userId") String userId, @Header("token") String token ,
                                                                        @Query("stat") String stat , @Query("page") String page);

    /**
     * 评论需求方
     * @return
     */
    @FormUrlEncoded
    @POST("Worker/worker_comments_boss")
    Observable<BaseResponseEntity<Object>> evaluateBoss(@Header("userId") String userId, @Header("token") String token ,
                                                        @Field("pt_id") String pt_id,@Field("com_content") String com_content,@Field("com_imgs") String com_imgs,@Field("score") String score);

    /**
     * 评论工人
     * @return
     */
    @FormUrlEncoded
    @POST("Boss/boss_comments_worker")
    Observable<BaseResponseEntity<Object>> evaluateWork(@Header("userId") String userId, @Header("token") String token ,
                                                        @Field("pt_id") String pt_id,@Field("com_content") String com_content,@Field("com_imgs") String com_imgs,@Field("score") String score);


    /**
     * 我的发布
     * @param userId
     * @param token
     * @param stat
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("Publishproject/boss_project")
    Observable<BaseResponseEntity<PublicProjectBean>> bossProjectList(@Header("userId") String userId, @Header("token") String token ,
                                                                            @Field("stat") String stat , @Field("page") String page);

}
