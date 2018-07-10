package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.SearchProjectContract
import com.weibiaogan.litong.mvp.model.SearchProjectModel

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:
 */
class SearchProjectPresenter : SearchProjectContract.Presenter(){
    override fun searchProject(pt_name: String, page: String, lat : String , lng : String) {
        getModel().searchProject(pt_name,page,lat, lng)
                .apply {
                    if (page == "1"){
                        loadDefulat(getView()!!)
                    }
                }
                .ui({
                    getView()?.getSearchResult(it.data!!)
                }, {
                    getView()?.showToast(it)
                })
    }

    override fun createModel(): SearchProjectContract.Model = SearchProjectModel()

}