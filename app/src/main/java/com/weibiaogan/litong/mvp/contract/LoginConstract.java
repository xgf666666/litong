package com.weibiaogan.litong.mvp.contract;

import com.weibiaogan.litong.entity.LoginBean;
import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;

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

        public abstract void loginThree(String type,String openid);
    }

    interface Model {

        void sendCode(@NotNull String mobile, @NotNull XxBaseHttpObserver<Object> xxBaseHttpObserver);

        void login(@NotNull String mobile, @Nullable String password, @NotNull XxBaseHttpObserver<LoginBean> xxBaseHttpObserver);

        Observable<BaseResponseEntity<Object>> loginThree(String type,String openid);
    }
}
