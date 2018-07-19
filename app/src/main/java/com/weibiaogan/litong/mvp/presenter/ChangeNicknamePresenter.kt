package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import android.util.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.ChangeNicknameContract
import com.weibiaogan.litong.mvp.model.ChangeNicknameModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改昵称
 */
class ChangeNicknamePresenter :  ChangeNicknameContract.Presenter() {
    override fun changeUserInfo() {
        val name: String? = getView()?.name

        if (TextUtils.isEmpty(name)) {
            getView()?.showToast("名字不能为空")
            return
        }

        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            Log.i("fadfagafaf",userId+"跟"+token)
            val map = mapOf("nickname" to name!!)
            getModel().updateUser(userId, token, name)
                    .loadDefulat(getView()!!)
                    .ui({
                        Log.i("getView",it.msg)
                        getView()?.successful(name)
                    }, {
                        getView()?.showToast(it)
                    })
        } else {
            getView()?.showToast("请先登录")
        }
    }

    override fun createModel(): ChangeNicknameContract.Model {
        return ChangeNicknameModel()
    }
}
