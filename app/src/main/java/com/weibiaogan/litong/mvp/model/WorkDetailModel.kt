package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.contract.WorkListConstract

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class WorkDetailModel : WorkDetailConstract.Model{
    override fun workDetail(userId: String, token: String, worker_user_id: String, lat: String, lnt: String) = AppApi.Api().workDetail(userId,token,worker_user_id,lat,lnt)
}
