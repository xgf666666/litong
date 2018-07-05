package com.weibiaogan.litong.mvp.contract;

import com.weibiaogan.litong.entity.LoginBean;
import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:
 */
public interface LoginConstract {

    interface View extends BaseMvpView {
        public String getMobile();

        public String getPassword();

        void loginSuccess();
    }

    abstract class Presenter extends BaseMvpPresenter<Model,View> {

        public  abstract  void login();
    }

    interface Model {

        void sendCode(@NotNull String mobile, @NotNull XxBaseHttpObserver<Object> xxBaseHttpObserver);

        void login(@NotNull String mobile, @Nullable String password, @NotNull XxBaseHttpObserver<LoginBean> xxBaseHttpObserver);
    }
}
