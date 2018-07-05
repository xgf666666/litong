package com.weibiaogan.litong.mvp.contract;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;
import com.xx.baseuilibrary.mvp.BaseMvpView;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import java.util.List;

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改绑定手机
 */
public interface ModifyBindContract {
    interface View extends BaseMvpView {

    }

    abstract class Presenter extends BaseMvpPresenter<Model,View> {
        public   abstract void getData();

    }

    interface Model{
        void getData(int page, XxBaseHttpObserver<List<Object>> httpObserver);

    }
}

