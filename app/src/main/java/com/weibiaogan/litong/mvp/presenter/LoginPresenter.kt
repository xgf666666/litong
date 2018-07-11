package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.common.md5Salt
import com.weibiaogan.litong.entity.LoginBean
import com.weibiaogan.litong.mvp.contract.LoginConstract
import com.weibiaogan.litong.mvp.model.LoginModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:
 */
class LoginPresenter :  LoginConstract.Presenter() {

    override fun login() {
        val mobile = getView()?.mobile
        val password = getView()?.password

        if (TextUtils.isEmpty(mobile)) {
            getView()?.showToast("请输入手机号")
            return
        }else if (mobile?.length != 11){
            getView()?.showToast("请输入正确的手机号")
        }

        if (TextUtils.isEmpty(password) || password?.length ?: 0 < 5) {
            getView()?.showToast("请输入正确的密码")
            return
        }

        val encryptMD5ToString =password?.md5Salt()
        getModel().login(mobile!!,encryptMD5ToString, object : XxBaseHttpObserver<LoginBean>() {
            override fun onCompleted(msg: String?, entity: LoginBean?) {
                getView()?.showToast(msg)
                Constants.putToken(entity)
                Constants.putPhone(mobile)
                Constants.login()
                getView()?.loginSuccess()
            }

            override fun onError(error: String?) {
                getView()?.showToast(error)
            }

            override fun onStart() {
                getView()?.showLoadingDialog()
            }

            override fun onFinish() {
                getView()?.dismissLoadingDialog()
            }

        })
    }


    override fun createModel(): LoginConstract.Model {
        return LoginModel()
    }
}
