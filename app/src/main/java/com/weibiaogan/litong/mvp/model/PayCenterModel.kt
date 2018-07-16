package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.App
import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.MemberBean
import com.weibiaogan.litong.entity.PayBean
import com.weibiaogan.litong.mvp.contract.PayCenterConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class PayCenterModel : PayCenterConstract.Model {
    override fun pay(userId: String, token: String, ptId: String, payType: String, ptType: String)=AppApi.Api().pay(userId,token,ptId,payType,ptType)

    override fun vip(userId: String, token: String): Observable<BaseResponseEntity<MemberBean>> {
        return AppApi.Api().vip(userId,token)


    }
}