package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.PayCenterConstract
import com.weibiaogan.litong.mvp.model.PayCenterModel
import com.weibiaogan.litong.utils.showToast
import com.xx.anypay.XxAnyPay
import com.xx.anypay.XxAnyPay.Companion.XXPAY_ALI
import com.xx.anypay.XxAnyPay.Companion.XXPAY_WX
import com.xx.anypay.XxAnyPayResultCallBack

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class PayCenterPresenter : PayCenterConstract.Presenter() {
    override fun pay(ptId: String, payType: String, ptType: String) {
        var userId=Constants.getToken().user_id.toString()
        var token=Constants.getToken().token
        getModel().pay(userId,token,ptId,payType,ptType).ui({
            getView()?.payResult(it.data!!)
        },{
            getView()?.showToast(it)
        })


    }

    override fun createModel(): PayCenterConstract.Model =PayCenterModel()
    override fun vip( userId:String, token:String){
        getModel().vip(userId,token).ui({
            Log.i("addddddddd",it.msg)
            getView()?.setView(it.data!!)

        },{
            getView()?.showToast(it)
        })

    }
}