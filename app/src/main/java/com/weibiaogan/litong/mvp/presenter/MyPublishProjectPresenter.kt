package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.MyPublishProjectContract
import com.weibiaogan.litong.mvp.model.MyPublishProjectModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.xx.baseuilibrary.mvp.BaseMvpPresenter

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:
 */
class MyPublishProjectPresenter : MyPublishProjectContract.Presenter() {
    override fun bossProjectList(stat: String, page: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().bossProjectList(userId, token,stat,  page)
                    .loadDefulatRefresh(page == "1",getView()!!)
                    .ui({
                        getView()?.getBossProjectList(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun cancelProject(pt_id: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().cancelProject(userId, token,pt_id)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.cancelProject(it?.msg!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): MyPublishProjectContract.Model =MyPublishProjectModel()
}