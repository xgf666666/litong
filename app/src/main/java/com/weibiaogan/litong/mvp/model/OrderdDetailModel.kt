package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.mvp.contract.OrdersDetailCOntract
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderdDetailModel:OrdersDetailCOntract.Model {
    interface View : BaseMvpView {

    }
    abstract class Presenter : BaseMvpPresenter<Model, View>(){

    }
    interface Model{

    }
}