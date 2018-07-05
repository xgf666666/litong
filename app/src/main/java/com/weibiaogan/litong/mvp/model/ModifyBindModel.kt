package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.ModifyBindContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改绑定手机
 */
class ModifyBindModel : ModifyBindContract.Model {
    override fun getData(page: Int, httpObserver: XxBaseHttpObserver<MutableList<Any>>?) {
        AppApi.Api().list(page)
                .flatMap {
                    Observable.just(BaseResponseEntity(1, mutableListOf(Any())))
                }
                .compose(RxHelper.io_main())
                .subscribe(httpObserver as Observer<in BaseResponseEntity<MutableList<Any>>>)
    }
}

