package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.HistoryprojectContract
import com.weibiaogan.litong.mvp.model.HistoryProjectModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class HistoryprojectPresenter:HistoryprojectContract.Presenter() {
    override fun historyProject(stat: String, page: String, type: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().historyProject(userId, token,stat, Constants.getLocation()[0],Constants.getLocation()[1], page, type)
                    .apply {
                        if (page == "1"){
                            loadDefulat(getView()!!)
                        }
                    }
                    .ui({
                        getView()?.getProjectBean(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): HistoryprojectContract.Model =HistoryProjectModel()
}