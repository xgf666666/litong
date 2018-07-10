package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.StoreListBean
import com.weibiaogan.litong.mvp.contract.StoreListConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class StoreListModel : StoreListConstract.Model{
    override fun storeList(userId: String, token: String, page: String, lat: String, lng: String,type : String): Observable<BaseResponseEntity<List<StoreListBean>>> {
        return AppApi.Api().storeList(userId, token, page, lat, lng,type)
    }
}
