package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.mvp.contract.BosIdentyContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/12
 * describe:
 */
class BosIdentyModel :BosIdentyContract.Model{
    override fun imgup(imagBase64: String): Observable<BaseResponseEntity<ImageBean>> =  AppApi.Api().imgup(imagBase64)



    override fun bosIdenty(userId:String ,token:String,map: Map<String,String>) =AppApi.Api().renzhengBoss(userId,token,map)
}