package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.common.AppService
import com.weibiaogan.litong.entity.WorkListBean
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkListModel : WorkListConstract.Model{
    override fun workerList(userId: String, token: String, page: String, lat: String, lng: String): Observable<BaseResponseEntity<List<WorkListBean>>>
    = AppApi.Api().workList(userId, token, page, lat, lng)

}
