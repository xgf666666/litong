package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.OrdersDetailCOntract
import com.weibiaogan.litong.mvp.model.OrderdDetailModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderDetailPresenter:OrdersDetailCOntract.Presenter() {
    override fun createModel(): OrdersDetailCOntract.Model {
        return OrderdDetailModel()
    }
}