package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.applyv2
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.model.WorkListModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.weibiaogan.litong.utils.showToast

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkListPresenter : WorkListConstract.Presenter(){
    override fun workerList(page: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().workerList(userId, token, page,Constants.getLocation()[0],Constants.getLocation()[1])

                    /*.doOnSubscribe {
                        if (page == "1"){
                        getView()?.showLoadingDialog()}
                    }
                    .doOnComplete {
                        if (page=="1")
                        getView()?.dismissLoadingDialog() }
                    .doOnError {
                        if (page=="1")
                        getView()?.dismissLoadingDialog() }*/
                    .loadDefulatRefresh(page == "1" , getView()!!)
                    /*.applyv2{
                        if (page == "1"){
                             loadDefulat(getView()!!)
                        }
                        this
                    }*/
                    .ui({
                        getView()?.getWorkListData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): WorkListConstract.Model = WorkListModel()
}
