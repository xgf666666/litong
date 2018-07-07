package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.EvaluateConstract
import com.weibiaogan.litong.mvp.contract.StoreListConstract
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.model.EvaluateModel
import com.weibiaogan.litong.mvp.model.StoreListModel
import com.weibiaogan.litong.mvp.model.WorkDetailModel
import com.weibiaogan.litong.mvp.model.WorkListModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class EvaluatePresenter : EvaluateConstract.Presenter(){
    override fun createModel(): EvaluateConstract.Model = EvaluateModel()
}