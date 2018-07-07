package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.entity.HomeBean
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
        getModel().getHomeData(page,lat, lnt, object : XxBaseHttpObserver<HomeBean>() {
            override fun onCompleted(msg: String?, entity: HomeBean?) {
                getView()?.setData(entity)
            }

            override fun onError(error: String?) {
                getView()?.showToast(error)
            }

            override fun onStart() {
                getView()?.showLoadingDialog()
            }

            override fun onFinish() {
                getView()?.dismissLoadingDialog()
            }

        })
    }


    override fun createModel(): HomeConstract.Model {
        return HomeModel()
    }




}
