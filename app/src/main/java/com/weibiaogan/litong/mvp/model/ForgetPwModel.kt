package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.ForgetPwContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observer

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:忘记密码
 */
class ForgetPwModel : ForgetPwContract.Model {
    override fun sendCode(phone: String?, param: XxBaseHttpObserver<Any>?) {
        AppApi.Api().sendCode(phone)
                .compose(RxHelper.io_main())
                .compose(RxHelper.start_finish(param))
                .subscribe(param as Observer<in BaseResponseEntity<Any>>)
    }

    override fun changePw(phone: String?, vCode: String?, newPw: String?, xxBaseHttpObserver: XxBaseHttpObserver<Any>) {
        AppApi.Api().forgetPwd(phone,vCode,newPw)
                .compose(RxHelper.io_main())
                .compose(RxHelper.start_finish(xxBaseHttpObserver))
                .subscribe(xxBaseHttpObserver)
    }
}
