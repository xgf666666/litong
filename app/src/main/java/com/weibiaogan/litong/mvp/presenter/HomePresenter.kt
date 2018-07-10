package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.weibiaogan.litong.mvp.model.HomeModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import kotlin.math.ln

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:首页
 */
class HomePresenter : HomeConstract.Presenter() {
    override fun getHomeData(page: String, lat: String, lnt: String) {
        AppApi.Api().homeData(page,lat, lnt)
                .loadDefulat(getView()!!)
                .ui({
                    getView()?.setData(it.data)
                }, {
                    getView()?.showToast(it)
                })
    }


    override fun createModel(): HomeConstract.Model {
        return HomeModel()
    }




}
