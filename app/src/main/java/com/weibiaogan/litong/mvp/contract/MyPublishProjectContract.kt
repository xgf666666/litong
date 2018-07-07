package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:
 */
interface MyPublishProjectContract {
    interface View : BaseMvpView {

    }
    abstract class Presenter : BaseMvpPresenter<Model, View>(){

    }
    interface Model{

    }
}