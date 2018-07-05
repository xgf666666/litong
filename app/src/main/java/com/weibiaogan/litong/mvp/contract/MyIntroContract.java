package com.weibiaogan.litong.mvp.contract;

import android.arch.lifecycle.LiveData;

import com.weibiaogan.litong.entity.ImageBean;
import com.weibiaogan.litong.entity.UserCenterBean;
import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.entity.ApiResponse;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我的信息
 */
public interface MyIntroContract {
    interface View extends BaseMvpView {
        public void setData(UserCenterBean o);

        void successful();
    }

    abstract class Presenter extends BaseMvpPresenter<Model, View> {
        public abstract void getUserData();

        public abstract void fileStore(@Nullable File file);

        public abstract void updateUser(@NotNull String sex);
    }

    interface Model {
        void getData(int page, XxBaseHttpObserver<List<Object>> httpObserver);

        @NotNull
        Observable<BaseResponseEntity<UserCenterBean>> UserIndex(@NotNull String userId, @Nullable String token);
        LiveData<ApiResponse<BaseResponseEntity<UserCenterBean>>> UserIndex2(@NotNull String userId, @Nullable String token);

        @NotNull
        Observable<BaseResponseEntity<Object>> updateUser(@NotNull String userId, @Nullable String token, @NotNull Map<String, String> map);

        @NotNull
        Observable<BaseResponseEntity<ImageBean>> imgup(@NotNull String imagBase64);
    }
}
