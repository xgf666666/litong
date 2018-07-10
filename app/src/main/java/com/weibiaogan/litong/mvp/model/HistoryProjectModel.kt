package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ProjectBean
import com.weibiaogan.litong.mvp.contract.HistoryprojectContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class HistoryProjectModel:HistoryprojectContract.Model {
    override fun historyProject(userId: String, token: String, stat: String, lat: String, lng: String, page: String, type: String): Observable<BaseResponseEntity<List<ProjectBean>>> {
        return AppApi.Api().historyProject(userId, token, stat, lat, lng, page, type)
    }
}