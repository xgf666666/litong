package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.LoginBean
import com.weibiaogan.litong.mvp.contract.LoginConstract
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:
 */
class LoginModel : LoginConstract.Model {
    override fun login(mobile: String, password: String?, xxBaseHttpObserver: XxBaseHttpObserver<LoginBean>) {
        AppApi.Api().login(password,mobile)
                .compose(RxHelper.io_main())
                .compose(RxHelper.start_finish(xxBaseHttpObserver))
                .subscribe(xxBaseHttpObserver)
    }

    override fun sendCode(mobile: String, xxBaseHttpObserver: XxBaseHttpObserver<Any>) {

    }
}
