package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.RegisterContracct
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observer

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:绑定手机 新用户注册
 */
class RegisterModel : RegisterContracct.Model {


    override fun register(phone: String?, pwd: String?, code: String?, yzm: String?, httpObserver: XxBaseHttpObserver<Any>?) {
        AppApi.Api().register(phone,pwd,code,yzm)
                .compose(RxHelper.io_main())
                .compose(RxHelper.start_finish(httpObserver))
                .subscribe(httpObserver as Observer<in BaseResponseEntity<Any>>)
    }

    override fun sendCode(phone: String?, httpObserver: XxBaseHttpObserver<Any>?) {
       AppApi.Api().sendCode(phone)
               .compose(RxHelper.io_main())
               .compose(RxHelper.start_finish(httpObserver))
               .subscribe(httpObserver as Observer<in BaseResponseEntity<Any>>)
    }

}
