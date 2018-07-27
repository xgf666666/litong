package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.model.WorkDetailModel
import com.weibiaogan.litong.mvp.contract.WorkEvaluateConstract
import com.weibiaogan.litong.mvp.model.WorkEvaluateModel
import com.weibiaogan.litong.mvp.model.WorkListModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.weibiaogan.litong.utils.showToast

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkEvaluatePresenter : WorkEvaluateConstract.Presenter(){
    override fun workEvaluate(work_user_id: String,page : String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().workEvaluate(userId, token, work_user_id, page)
                    .loadDefulatRefresh(page == "1",getView()!!)
                    .ui({
                        getView()?.getWorkEvaluateData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun projectEvaluate(work_user_id: String, page: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().projectEvaluate(userId, token, work_user_id, page)
                    .loadDefulatRefresh(page == "1",getView()!!)
                    .ui({
                        getView()?.getWorkEvaluateData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun createModel(): WorkEvaluateConstract.Model = WorkEvaluateModel()
}