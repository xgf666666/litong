package com.weibiaogan.litong.mvp.contract;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import io.reactivex.Observable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改昵称
 */
public interface ChangeNicknameContract {
    interface View extends BaseMvpView {

        @NotNull
        String getName();

        void successful(String name);
    }

    public abstract class Presenter extends BaseMvpPresenter<Model, View> {

        public abstract void changeUserInfo();
    }

    interface Model {

        void changeUserInfo(@Nullable String name, @NotNull XxBaseHttpObserver<Object> xxBaseHttpObserver);

        @NotNull
        Observable<BaseResponseEntity<Object>> updateUser(@NotNull String s, @Nullable String token, @NotNull String nickname);
    }
}
