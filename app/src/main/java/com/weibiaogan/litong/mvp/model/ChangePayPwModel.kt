package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.ChangePayPwContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改支付密码
 */
class ChangePayPwModel : ChangePayPwContract.Model {

    override fun settingAndChangePayPwd(userId: String, token: String, code: String, balance_payment: String, xxBaseHttpObserver: XxBaseHttpObserver<Any>) {
        AppApi.Api().updateBalancePayment(userId,token,code,balance_payment)
                .compose(RxHelper.io_main())
                .compose(RxHelper.start_finish(xxBaseHttpObserver))
                .subscribe(xxBaseHttpObserver)
    }


    override fun sendVCode(phone: String, xxBaseHttpObserver: XxBaseHttpObserver<Any>) {
        AppApi.Api().sendCode(phone).compose(RxHelper.io_main()).subscribe(xxBaseHttpObserver)
    }

    override fun getData(page: Int, httpObserver: XxBaseHttpObserver<List<Any>>) {

        AppApi.Api().list(page)
                .flatMap {
                    Observable.just(BaseResponseEntity(1, listOf(Any())))
                }
                .compose(RxHelper.io_main())
                .subscribe(httpObserver)
    }
}
