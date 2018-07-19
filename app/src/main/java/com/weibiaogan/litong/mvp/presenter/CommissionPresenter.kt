package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.common.md5Salt
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.CommissionContract
import com.weibiaogan.litong.mvp.model.CommissionModel
import com.weibiaogan.litong.utils.showToast

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:佣金金额
 */
class CommissionPresenter : CommissionContract.Presenter() {
    override fun commission(cash: String, account: String,type:String,balancePayment:String) {

        val balancePaymentMD5 = balancePayment.md5Salt()
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().withdrawAdd(userId, token,cash,account,type,balancePaymentMD5).loadDefulat(getView()!!)
                    .ui({
                        if (it.status.equals("1")){
                            getView()?.successful()
                        }else{
                            getView()?.showToast(it.msg)
                        }

                    }, {
                        getView()?.showToast(it)
                    })
        } else {
            getView()?.showToast("请先登录")

        }

    }

    override fun getData() {

    }

    override fun createModel(): CommissionContract.Model {
        return CommissionModel()
    }
}
