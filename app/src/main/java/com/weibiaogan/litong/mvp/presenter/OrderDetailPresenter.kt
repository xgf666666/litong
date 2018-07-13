package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.OrdersDetailCOntract
import com.weibiaogan.litong.mvp.model.OrderdDetailModel
import com.weibiaogan.litong.utils.loadDefulatRefresh

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderDetailPresenter:OrdersDetailCOntract.Presenter() {
    override fun orderDetail(pt_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().orderDetail(userId, token, pt_id)
                    .loadDefulat(getView()!!)
                    .ui({
                        getView()?.getOrderDetail(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun addBlack(worker_user_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().addBlack(userId, token, worker_user_id)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.showToast(it?.msg!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun clickReceipt(pt_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().clickReceipt(userId, token, pt_id)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.showToast(it?.msg!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): OrdersDetailCOntract.Model {
        return OrderdDetailModel()
    }
}