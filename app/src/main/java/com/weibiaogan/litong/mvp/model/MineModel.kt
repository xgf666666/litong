package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.MineContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我的酒馆
 */
class MineModel : MineContract.Model {
    override fun UserIndex(userId: String, token: String?) =
            AppApi.Api().UserIndex(userId, token)

    override fun imgup(imagBase64: String) =
            AppApi.Api().imgup(imagBase64)

    override fun getData(page: Int, httpObserver: XxBaseHttpObserver<MutableList<Any>>?) {
        AppApi.Api().list(page)
                .flatMap {
                    Observable.just(BaseResponseEntity(1, mutableListOf(Any())))
                }
                .compose(RxHelper.io_main())
                .subscribe(httpObserver as Observer<in BaseResponseEntity<MutableList<Any>>>)
    }
}
