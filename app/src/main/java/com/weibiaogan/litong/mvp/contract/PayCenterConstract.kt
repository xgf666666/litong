package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:支付中心
 */
interface PayCenterConstract {
    interface View :BaseMvpView{

    }
    abstract class Presenter :BaseMvpPresenter<Model,View>(){

    }
    interface Model{

    }

}