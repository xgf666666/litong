package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.model.WorkListModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkListPresenter : WorkListConstract.Presenter(){
    override fun workerList(page: String, lat: String, lng: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().workerList(userId, token, page, lat, lng).loadDefulat(getView()!!)
                    .ui({
                        getView()?.getWorkListData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): WorkListConstract.Model = WorkListModel()
}
