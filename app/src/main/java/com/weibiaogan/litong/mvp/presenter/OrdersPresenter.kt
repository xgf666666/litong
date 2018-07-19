package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.weibiaogan.litong.mvp.model.OrdersModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.weibiaogan.litong.utils.showToast

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
                    .loadDefulatRefresh(page == "1",getView()!!)
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
