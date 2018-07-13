package com.weibiaogan.litong.ui.blacklist

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.BlackBean
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
class BlacklistModel : BlacklistContract.Model {
    override fun getData(userId: String, token: String, page: String, lat: String, lng: String): Observable<BaseResponseEntity<List<BlackBean>>> {
        return AppApi.Api().backlist(userId,token,page,lat, lng)
    }

    override fun delBack(userId: String, token: String, id: String): Observable<BaseResponseEntity<Any?>?>? {
        return AppApi.Api().delBack(userId, token, id)
    }



}
