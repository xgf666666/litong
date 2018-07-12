package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.SearchProjectContract
import com.weibiaogan.litong.mvp.model.SearchProjectModel
import com.weibiaogan.litong.utils.loadDefulatRefresh

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:
 */
class SearchProjectPresenter : SearchProjectContract.Presenter(){
    override fun searchProject(pt_name: String, page: String) {
        getModel().searchProject(pt_name,page, Constants.getLocation()[0], Constants.getLocation()[1])
                .loadDefulatRefresh(page == "1",getView()!!)
                .ui({
                    getView()?.getSearchResult(it.data!!)
                }, {
                    getView()?.showToast(it)
                })
    }

    override fun createModel(): SearchProjectContract.Model = SearchProjectModel()

}