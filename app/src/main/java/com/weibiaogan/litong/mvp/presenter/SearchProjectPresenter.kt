package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.SearchProjectContract
import com.weibiaogan.litong.mvp.model.SearchProjectModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:
 */
class SearchProjectPresenter : SearchProjectContract.Presenter(){
    override fun createModel(): SearchProjectContract.Model = SearchProjectModel()

}