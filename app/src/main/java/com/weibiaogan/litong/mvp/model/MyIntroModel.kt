package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.AddShopBean
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.mvp.contract.MyIntroContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我的信息
 */
class MyIntroModel : MyIntroContract.Model {


    //退出登录
    override fun loginOff(userId: String, token: String?)=
    AppApi.Api().loginOff(userId,token)

    //修改性别
    override fun updateUserSex(userId: String, token: String?, map: String) =
            AppApi.Api().updateUserSex(userId,token, map)

    override fun imgup(imagBase64: String) =
            AppApi.Api().imgup(imagBase64)

    override fun updateUser(userId: String, token: String?, map: String) =
            AppApi.Api().updateUser("$userId", token, map)

    override fun UserIndex(userId: String, token: String?) =
            AppApi.Api().UserIndex(userId, token)


    override fun UserIndex2(userId: String, token: String?) =
            AppApi.Api().UserIndex2(userId, token)

    override fun getData(page: Int, httpObserver: XxBaseHttpObserver<MutableList<Any>>?) {
        AppApi.Api().list(page)
                .flatMap {
                    Observable.just(BaseResponseEntity(1, mutableListOf(Any())))
                }
                .compose(RxHelper.io_main())
                .subscribe(httpObserver as Observer<in BaseResponseEntity<MutableList<Any>>>)
    }
}
