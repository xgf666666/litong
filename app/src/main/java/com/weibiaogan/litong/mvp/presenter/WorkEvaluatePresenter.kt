package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.model.WorkDetailModel
import com.weibiaogan.litong.mvp.contract.WorkEvaluateConstract
import com.weibiaogan.litong.mvp.model.WorkEvaluateModel
import com.weibiaogan.litong.mvp.model.WorkListModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkEvaluatePresenter : WorkEvaluateConstract.Presenter(){
    override fun createModel(): WorkEvaluateConstract.Model = WorkEvaluateModel()
}