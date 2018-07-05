package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我要接单
 */
interface OrdersContract {
    interface View : BaseMvpLcecView<List<Any>> {


    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {

    }

    interface Model {


    }
}
