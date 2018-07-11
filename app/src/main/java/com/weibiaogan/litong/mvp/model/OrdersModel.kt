package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ProjectBean
import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我要接单
 */
class OrdersModel : OrdersContract.Model {
    override fun historyProject(userId: String, token: String, stat: String, lat: String, lng: String, page: String, type: String): Observable<BaseResponseEntity<List<ProjectBean>>> {
        return AppApi.Api().historyProject(userId, token, stat, lat, lng, page, type)
    }

}
