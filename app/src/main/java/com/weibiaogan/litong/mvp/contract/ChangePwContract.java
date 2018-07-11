package com.weibiaogan.litong.mvp.contract;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;
import com.xx.baseutilslibrary.network.rx.RxHttpObserver;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改密码
 */
public interface ChangePwContract {
    interface View extends BaseMvpView {
        public  String getNewPassword();

        public  String getCheckPassword();

        public void changeSuccess();

        String getCode();

        String getphone();

        void setSendBtnEnable(boolean b);

        void setSendBtnText(@NotNull String s);
    }

    abstract class Presenter extends BaseMvpPresenter<Model,View>{

        public    abstract    void changePw();

        public   abstract   void sendVCode();
    }

    interface Model{

        Observable<BaseResponseEntity<Object>> forgetUserPwd(@Nullable String userId, @Nullable String token, @Nullable String code, @Nullable String new_user_pwd);

        void sendVCode(@NotNull String phone, @NotNull XxBaseHttpObserver<Object> xxBaseHttpObserver);
    }
}
