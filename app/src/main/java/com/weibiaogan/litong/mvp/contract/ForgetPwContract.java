package com.weibiaogan.litong.mvp.contract;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:忘记密码
 */
public interface ForgetPwContract {
    interface View extends BaseMvpView {
        public String getPhone();

        public String getCode();

        public String getNewPassword();

        public String getCheckPassword();

        void setSendBtnEnable(boolean b);

        void setSendBtnText(@NotNull String str);

        void changeSuccess();
    }

    abstract class Presenter extends BaseMvpPresenter<Model,View> {
        public abstract void sendCode();

        public abstract void  changePw() ;
    }

    interface Model {
        public void sendCode(String phone, XxBaseHttpObserver<Object> param);

        void changePw(@Nullable String phone, @Nullable String vCode, @Nullable String newPw, @NotNull XxBaseHttpObserver<Object> xxBaseHttpObserver);
    }
}
