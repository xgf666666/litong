package com.weibiaogan.litong.mvp.contract;

import com.weibiaogan.litong.entity.ImageBean;
import com.weibiaogan.litong.entity.UserCenterBean;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView;
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我的酒馆
 */
public interface MineContract {
    interface View extends BaseMvpLcecView<UserCenterBean> {

        void setData(@Nullable UserCenterBean data);

    }

    abstract class Presenter extends BaseMvpPresenter<Model,View>{
        public abstract   void getData();

        public  abstract   void fileStore(@Nullable File file);
    }

    interface Model{
        void getData(int page, XxBaseHttpObserver<List<Object>> httpObserver);

        @NotNull
        Observable<BaseResponseEntity<ImageBean>> imgup(@NotNull String imagBase64);

        @NotNull
        Observable<BaseResponseEntity<UserCenterBean>> UserIndex(@NotNull String userId, @Nullable String token);
    }
}
