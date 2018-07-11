package com.weibiaogan.litong.mvp.model
import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.WorkerIdentyTwoContrat
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class WorkerIdentyTwoModel:WorkerIdentyTwoContrat.Model {
    override fun renZhengWork(userId: String, token: String, map: Map<String, String>)=AppApi.Api().renzhengWorker(userId,token,map)

    override fun getWorkerTyle(userId: String, token: String)= AppApi.Api().workerTyle(userId,token)




}