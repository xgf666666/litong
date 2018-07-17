package com.weibiaogan.litong.mvp.contract;

import com.weibiaogan.litong.entity.LoginBean;
import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import io.reactivex.Observable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:绑定手机 新用户注册
 */
public interface RegisterContracct {
    interface View extends BaseMvpView {
        String getPhone();

        String getCode();

        String getNewPassword();

        String getCheckPassword();

        String getInvite();

        void onSendCodeSuccess(String msg);

        void onRegisterSuccess(String msg);

        void onBindSuccess();


    }

    abstract class Presenter extends BaseMvpPresenter<Model,View> {

        public   abstract   void sendCode();

        public abstract   void register();

        public abstract void regThree(String type,String openid);

    }

    interface Model {

        void register(String phone, String pwd,  String yzm, XxBaseHttpObserver<Object> httpObserver);

        void sendCode(String phone, XxBaseHttpObserver<Object> httpObserver);

        Observable<BaseResponseEntity<LoginBean>> regThree(String phone,String pwd,String code,String type, String openid);
    }
}
