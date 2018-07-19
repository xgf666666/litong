package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.StoreListConstract
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.contract.MyReceiptConstract
import com.weibiaogan.litong.mvp.model.MyReceiptModel
import com.weibiaogan.litong.mvp.model.StoreListModel
import com.weibiaogan.litong.mvp.model.WorkDetailModel
import com.weibiaogan.litong.mvp.model.WorkListModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.weibiaogan.litong.utils.showToast

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class MyReceiptPresenter : MyReceiptConstract.Presenter(){
    override fun workProjectList(stat: String, page: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().workProjectList(userId, token, stat, page)
                    .loadDefulatRefresh(page == "1" , getView()!!)
                    .ui({
                        getView()?.getProjectList(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })

            }
        }

    override fun createModel(): MyReceiptConstract.Model = MyReceiptModel()
}