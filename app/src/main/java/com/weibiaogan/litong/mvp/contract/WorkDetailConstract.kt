package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.HomeBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface WorkDetailConstract {

    public interface View : BaseMvpView {

    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {

    }

    public interface Model {

    }
}