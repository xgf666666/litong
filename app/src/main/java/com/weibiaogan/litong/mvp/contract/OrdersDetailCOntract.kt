package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:项目详情
 */
interface OrdersDetailCOntract {
    interface View : BaseMvpLcecView<List<Any>> {


    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {

    }

    interface Model {


    }
}