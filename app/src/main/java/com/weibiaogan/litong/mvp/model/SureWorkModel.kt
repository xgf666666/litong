package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.WorkDetailBean
import com.weibiaogan.litong.mvp.contract.SureWorkContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class SureWorkModel:SureWorkContract.Model {
    override fun workDetail(userId: String, token: String, worker_user_id: String, lat: String, lnt: String): Observable<BaseResponseEntity<WorkDetailBean>> {
        return AppApi.Api().workDetail(userId,token,worker_user_id,lat,lnt)
    }

    override fun bindWork(userId: String, token: String, pt_id: String): Observable<BaseResponseEntity<Any?>?>? {
        return AppApi.Api().bindWorker(userId, token, pt_id)
    }

    override fun dieWork(userId: String, token: String, pt_id: String): Observable<BaseResponseEntity<Any?>?>? {
        return AppApi.Api().dieWork(userId, token, pt_id)
    }
}