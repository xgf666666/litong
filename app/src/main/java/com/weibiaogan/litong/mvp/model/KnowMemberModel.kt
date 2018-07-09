package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.MemberpowrBean
import com.weibiaogan.litong.mvp.contract.KnowMemberCOntract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
class KnowMemberModel:KnowMemberCOntract.Model {
    override fun getvipContent(userId: String, token: String): Observable<BaseResponseEntity<MemberpowrBean>> {
        return AppApi.Api().vipPwor(userId,token)
    }
}