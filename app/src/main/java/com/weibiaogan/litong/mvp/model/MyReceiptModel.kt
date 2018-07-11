package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.MyReceiptBean
import com.weibiaogan.litong.mvp.contract.MyReceiptConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class MyReceiptModel : MyReceiptConstract.Model{
    override fun workProjectList(userId: String, token: String, stat: String, page: String): Observable<BaseResponseEntity<List<MyReceiptBean>>> {
        return AppApi.Api().workProjectList(userId, token, stat, page)
    }
}