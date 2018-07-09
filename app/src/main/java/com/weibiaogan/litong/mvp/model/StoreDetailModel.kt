package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.StoreDetailBean
import com.weibiaogan.litong.mvp.contract.StoreDetailConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class StoreDetailModel : StoreDetailConstract.Model{
    override fun storeDetail(userId: String, token: String, store_id: String, lat: String, lng: String): Observable<BaseResponseEntity<StoreDetailBean>> {
        return AppApi.Api().storeDetail(userId, token, store_id, lat, lng)
    }
}
