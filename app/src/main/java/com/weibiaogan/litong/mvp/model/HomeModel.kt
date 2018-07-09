package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observer

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:首页
 */
class HomeModel : HomeConstract.Model {
    override fun getHomeData(page: String, lat: String, lnt: String){
        //return AppApi.Api().homeData(page,lat, lnt)
    }


}
