package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import com.weibiaogan.litong.common.md5Salt
import com.weibiaogan.litong.mvp.contract.RegisterContracct
import com.weibiaogan.litong.mvp.model.RegisterModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:绑定手机 新用户注册
 */
class RegisterPresenter :  RegisterContracct.Presenter() {

    override fun sendCode() {
        if (TextUtils.isEmpty(getView()?.phone)){
            getView()?.showToast("请输入手机号")
            return
        }else if (11!=getView()?.phone!!.length){
            getView()?.showToast("请输入正确的手机号")
            return
        }

        getModel().sendCode(getView()?.phone,object : XxBaseHttpObserver<Any>(){

            override fun onCompleted(msg: String?, entity: Any?) {
                getView()?.showToast(msg)
                getView()?.onSendCodeSuccess(msg)
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

    override fun register() {
        val phone = getView()?.phone
        val code = getView()?.code
        val newPassword = getView()?.newPassword
        val checkPassword = getView()?.checkPassword
        val invite = getView()?.invite

        if (TextUtils.isEmpty(phone)){
            getView()?.showToast("请输入手机号")
            return
        }else if (phone!!.length!=11){
            getView()?.showToast("请输入正确的手机号")
            return
        }else if (TextUtils.isEmpty(code)){
            getView()?.showToast("请输入验证")
            return
        }else if (code!!.length!=6){
            getView()?.showToast("请输入6位验证码")
            return
        }else if (TextUtils.isEmpty(newPassword)){
            getView()?.showToast("请输入新密码")
            return
        }else if (TextUtils.isEmpty(checkPassword)){
            getView()?.showToast("请输入确认密码")
            return
        }else if (newPassword != checkPassword){
            getView()?.showToast("确认密码不一致")
            return
        }

        val encryptMD5ToString = newPassword?.md5Salt()

        getModel().register(phone,encryptMD5ToString,invite,code,object : XxBaseHttpObserver<Any>(){
            override fun onCompleted(msg: String?, entity: Any?) {
                getView()?.onRegisterSuccess(msg)
                getView()?.showToast(msg)
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

    override fun createModel(): RegisterContracct.Model {
        return RegisterModel()
    }
}
