package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.model.WorkDetailModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkDetailPresenter : WorkDetailConstract.Presenter(){
    override fun createModel(): WorkDetailConstract.Model = WorkDetailModel()
}