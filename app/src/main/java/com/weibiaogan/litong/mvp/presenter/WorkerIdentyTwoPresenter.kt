package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkerIdentyTwoContrat
import com.weibiaogan.litong.mvp.model.WorkerIdentyTwoModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class WorkerIdentyTwoPresenter:WorkerIdentyTwoContrat.Presenter() {
    override fun renZhengWork(map: Map<String, String>) {
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().renZhengWork(userId,token,map)?.ui({
            Log.i("chenggong",it?.msg)
            getView()?.succeful()
        },{
            getView()?.showToast(it.message)
        })
    }

    override fun getWorkerTyle() {
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().getWorkerTyle(userId,token)?.ui({
                getView()?.getListData(it.data!!)
        },{

        })

    }

    override fun createModel(): WorkerIdentyTwoContrat.Model =WorkerIdentyTwoModel()
}