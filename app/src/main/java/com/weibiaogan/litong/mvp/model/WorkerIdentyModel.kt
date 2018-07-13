package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.mvp.contract.WorkerIdentyContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class WorkerIdentyModel:WorkerIdentyContract.Model {
    override fun imgup(imagBase64: String): Observable<BaseResponseEntity<ImageBean>> =  AppApi.Api().imgup(imagBase64)

}