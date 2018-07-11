package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.mvp.contract.EvaluateConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class EvaluateModel : EvaluateConstract.Model{
    override fun imgup(imagBase64: String): Observable<BaseResponseEntity<ImageBean>> {
        return AppApi.Api().imgup(imagBase64)
    }

    override fun evaluateWork(userId: String, token: String, pt_id: String, com_content: String, com_imgs: String, score: String): Observable<BaseResponseEntity<Any?>?>? {
        return AppApi.Api().evaluateWork(userId, token, pt_id, com_content, com_imgs, score)
    }

    override fun evaluateBoss(userId: String, token: String, pt_id: String, com_content: String, com_imgs: String, score: String) =
            AppApi.Api().evaluateBoss(userId, token, pt_id, com_content, com_imgs, score)
}