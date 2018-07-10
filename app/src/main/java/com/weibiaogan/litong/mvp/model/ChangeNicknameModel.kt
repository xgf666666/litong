package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.ChangeNicknameContract
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改昵称
 */
class ChangeNicknameModel : ChangeNicknameContract.Model {
    override fun updateUser(userId: String, token: String?, nickname: String) =
            AppApi.Api().updateUserName("$userId", token, nickname)

    override fun changeUserInfo(name: String?, httpObserver: XxBaseHttpObserver<Any>) {

    }
}
