package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.SureWorkContract
import com.weibiaogan.litong.mvp.model.SureWorkModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class SureWorkerPresenter:SureWorkContract.Presenter() {
    override fun workDetail(worker_user_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().workDetail(userId, token, worker_user_id,Constants.getLocation()[0],Constants.getLocation()[1]).loadDefulat(getView()!!)
                    .ui({
                        getView()?.getWorkDetailData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun bindWork(pt_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().bindWork(userId, token,pt_id)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.sureWork(it?.msg!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun dieWork(pt_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().dieWork(userId, token,pt_id)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.sureWork(it?.msg!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): SureWorkContract.Model =SureWorkModel()
}