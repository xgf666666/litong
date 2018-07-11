package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.PublicProjectBean
import com.weibiaogan.litong.mvp.contract.MyPublishProjectContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:
 */
class MyPublishProjectModel :MyPublishProjectContract.Model{
    override fun bossProjectList(userId: String, token: String, stat: String, page: String): Observable<BaseResponseEntity<PublicProjectBean>> {
        return AppApi.Api().bossProjectList(userId, token, stat, page)
    }
}