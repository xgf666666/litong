package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.contract.StoreListConstract
import com.weibiaogan.litong.mvp.model.StoreListModel
import com.weibiaogan.litong.mvp.model.WorkDetailModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class StoreListPresenter : StoreListConstract.Presenter(){
    override fun createModel(): StoreListConstract.Model = StoreListModel()
}