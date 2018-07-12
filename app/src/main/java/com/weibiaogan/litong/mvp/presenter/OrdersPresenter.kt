package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.weibiaogan.litong.mvp.model.OrdersModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我要接单
 */
class OrdersPresenter :  OrdersContract.Presenter() {
    override fun historyProject(stat: String, page: String, type: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().historyProject(userId, token,stat,Constants.getLocation()[0],Constants.getLocation()[1], page, type)
                    .ui({
                        getView()?.setData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }


    override fun createModel(): OrdersContract.Model {
        return OrdersModel()
    }


}
