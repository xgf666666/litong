package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.ChangePwContract
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.RxHttpObserver
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改密码
 */
class ChangePwModel : ChangePwContract.Model {
    override fun sendVCode(phone: String, xxBaseHttpObserver: XxBaseHttpObserver<Any>) {
        AppApi.Api().sendCode(phone).compose(RxHelper.io_main()).subscribe(xxBaseHttpObserver)
    }

    override fun changePW(origin: String?, newPassword: String?, checkPassword: String?, rxHttpObserver: RxHttpObserver<Any>) {


    }
}
