package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.OrderDetailBean
import com.weibiaogan.litong.mvp.contract.OrdersDetailCOntract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderdDetailModel:OrdersDetailCOntract.Model {
    override fun orderDetail(userId: String, token: String, pt_id: String): Observable<BaseResponseEntity<OrderDetailBean>> {
        return AppApi.Api().projectDetail(userId, token, pt_id)
    }

    override fun addBlack(userId: String, token: String, worker_user_id: String): Observable<BaseResponseEntity<Any?>?>? {
        return AppApi.Api().addBack(userId, token, worker_user_id)
    }

    override fun clickReceipt(userId: String, token: String, pt_id: String): Observable<BaseResponseEntity<Any?>?>? {
        return AppApi.Api().clickReceipt(userId, token, pt_id)
    }

}