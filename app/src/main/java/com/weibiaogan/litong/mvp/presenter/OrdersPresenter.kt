package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.weibiaogan.litong.mvp.model.OrdersModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我要接单
 */
class OrdersPresenter :  OrdersContract.Presenter() {




    override fun createModel(): OrdersContract.Model {
        return OrdersModel()
    }


}
