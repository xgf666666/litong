package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.PayCenterConstract
import com.weibiaogan.litong.mvp.model.PayCenterModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class PayCenterPresenter : PayCenterConstract.Presenter() {
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