package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.ShareContract
import com.weibiaogan.litong.mvp.model.ShareModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/13
 * describe:
 */
class SharePresenter:ShareContract.Presenter() {
    override fun share() {
        var userId=Constants.getToken().user_id.toString()
        var token=Constants.getToken().token
        getModel().share(userId,token).ui({
            if (it.status.equals("1"))
            getView()?.succeful(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun createModel(): ShareContract.Model =ShareModel()
}