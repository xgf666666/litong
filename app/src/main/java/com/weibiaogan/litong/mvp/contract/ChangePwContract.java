package com.weibiaogan.litong.mvp.contract;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.rx.RxHttpObserver;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        void changePW(@Nullable String origin, @Nullable String newPassword, @Nullable String checkPassword, @NotNull RxHttpObserver<Object> rxHttpObserver);

        void sendVCode(@NotNull String phone, @NotNull XxBaseHttpObserver<Object> xxBaseHttpObserver);
    }
}
