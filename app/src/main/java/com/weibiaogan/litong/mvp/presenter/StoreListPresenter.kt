package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.contract.StoreListConstract
import com.weibiaogan.litong.mvp.model.StoreListModel
import com.weibiaogan.litong.mvp.model.WorkDetailModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.weibiaogan.litong.utils.showToast

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class StoreListPresenter : StoreListConstract.Presenter(){
    override fun storeList(page: String,type:String) {

        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().storeList(userId, token, page, Constants.getLocation()[0],Constants.getLocation()[1],type)
                    .loadDefulatRefresh(page == "1",getView()!!)
                    .ui({
                        getView()?.getStoreListData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): StoreListConstract.Model = StoreListModel()
}