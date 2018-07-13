package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ShareUserBean
import com.weibiaogan.litong.mvp.contract.ShareContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/13
 * describe:
 */
class ShareModel:ShareContract.Model {
    override fun share(userid: String, token: String)=AppApi.Api().share(userid,token)
}