package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.model.WorkDetailModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkDetailPresenter : WorkDetailConstract.Presenter(){
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
        //getModel().workDetail()
    }

    override fun createModel(): WorkDetailConstract.Model = WorkDetailModel()
}