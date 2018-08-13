package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.ShopAddContract
import com.weibiaogan.litong.mvp.model.ShopAddModel
import com.weibiaogan.litong.utils.showToast

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class ShopAddPresenter:ShopAddContract.Presenter() {
    override fun addShop( map: HashMap<String, String>) {
        var userId:String=Constants.getToken().user_id.toString()
        var token:String=Constants.getToken().token
        getModel().addShop(userId,token,map).ui({
            if (it.status.equals("1"))
            getView()?.addSucceeful(it.msg!!)
        },{
            getView()?.showToast(it)
        })

    }
    fun imgUp(imagBase64: String) {
        getModel().imgup(imagBase64).ui(
                {
                    getView()?.setView(it?.data?.imgUrl!!)
                }, {
            getView()?.showToast(it)
        }
        )
    }


    override fun createModel(): ShopAddContract.Model =ShopAddModel()
}