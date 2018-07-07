package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.model.WorkListModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkListPresenter : WorkListConstract.Presenter(){
    override fun createModel(): WorkListConstract.Model = WorkListModel()
}
