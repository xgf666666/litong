package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.weibiaogan.litong.mvp.contract.WorkEvaluateConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkEvaluateModel : WorkEvaluateConstract.Model{
    override fun workEvaluate(userId: String, token: String, work_user_id: String,page : String): Observable<BaseResponseEntity<WorkEvaluateBean>> {
        return AppApi.Api().workEvaluate(userId,token,work_user_id,page)
    }

    override fun projectEvaluate(userId: String, token: String, pt_user_id: String, page: String): Observable<BaseResponseEntity<WorkEvaluateBean>> {
        return AppApi.Api().projectEvaluate(userId, token, pt_user_id, page)
    }
}
