package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface MyReceiptConstract {

    public interface View : BaseMvpView {

    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {

    }

    public interface Model {

    }
}