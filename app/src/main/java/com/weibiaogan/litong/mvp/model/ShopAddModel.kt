package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.mvp.contract.ShopAddContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.util.HashMap

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class ShopAddModel:ShopAddContract.Model {
    override fun addShop(userId: String, token: String, map: HashMap<String, String>)=AppApi.Api().addShop(userId,token,map)
    override fun imgup(imagBase64: String): Observable<BaseResponseEntity<ImageBean>> =  AppApi.Api().imgup(imagBase64)

}