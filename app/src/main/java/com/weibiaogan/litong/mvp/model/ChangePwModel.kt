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
    override fun forgetUserPwd(userId: String?, token: String?, code: String?, new_user_pwd: String?) =
            AppApi.Api().forgetUserPwd(userId,token,code,new_user_pwd)

    override fun sendVCode(phone: String, xxBaseHttpObserver: XxBaseHttpObserver<Any>) {
        AppApi.Api().sendCode(phone).compose(RxHelper.io_main()).subscribe(xxBaseHttpObserver)
    }


}
