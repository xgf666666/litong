package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import android.util.Log
import com.blankj.utilcode.util.RegexUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.common.md5Salt
import com.weibiaogan.litong.mvp.contract.ChangePayPwContract
import com.weibiaogan.litong.mvp.model.ChangePayPwModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DefaultObserver
import java.util.concurrent.TimeUnit

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改支付密码
 */
class ChangePayPwPresenter :  ChangePayPwContract.Presenter() {
    override fun getData() {

    }

    override fun changePw() {
        val code = getView()?.getCode()

        if (TextUtils.isEmpty(code)) {
            getView()?.showToast("请输入验证码")
            return
        }
        if (code!!.length!=4){
            getView()?.showToast("请输入4位验证码")
            return
        }

        val checkPassword = getView()?.getCheckPassword()

        val newPassword = getView()?.getNewPassword()

        if (TextUtils.isEmpty(newPassword)){
            getView()?.showToast("请输入新密码")
            return
        }
        if (newPassword!!.length!=6){
            getView()?.showToast("请输入6位新密码")
            return
        }

        if (TextUtils.isEmpty(checkPassword)){
            getView()?.showToast("请输入确认密码")
            return
        }

        if (newPassword.length ?: 0 > 6) {
            if (newPassword == checkPassword) {
                getView()?.showToast("新密码两次输入不一样")
                return
            }
        }

        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token

        val encryptMD5ToString = newPassword.md5Salt()

        getModel().settingAndChangePayPwd(userId,token, code,encryptMD5ToString,object : XxBaseHttpObserver<Any>(){
            override fun onCompleted(msg: String?, entity: Any?) {
                getView()?.showToast(msg)
                getView()?.changeSuccess()
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


    override fun sendVCode() {
        val phone = getView()?.getphone()!!
        if (!RegexUtils.isMobileSimple(phone)) {
            getView()?.showToast("请输入正确的手机号")
            return
        }


        getModel().sendVCode(phone, object : XxBaseHttpObserver<Any>() {
            override fun onCompleted(msg: String?, entity: Any?) {
                getView()?.showToast(msg)
                //开始倒计时
                val timeCount = 60
                Observable.interval(0, 1, TimeUnit.SECONDS)
                        .take((timeCount + 1).toLong())
                        .map { aLong -> timeCount - aLong!! }
                        .doOnSubscribe {
                            //发送数据中,将发送按钮状态设置为不可用
                            Log.i("fagagag",it.toString())
                            getView()?.setSendBtnEnable(false)

                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : DefaultObserver<Long>() {
                            override fun onNext(value: Long) {
                                getView()?.setSendBtnText("(" + value + "s)")
                            }

                            override fun onError(e: Throwable) {
                                getView()?.setSendBtnEnable(true)
                                getView()?.setSendBtnText("重试")
                            }

                            override fun onComplete() {
                                getView()?.setSendBtnEnable(true)
                                getView()?.setSendBtnText("重新发送")
                            }
                        })
            }

            override fun onError(error: String?) {
                getView()?.showToast(error)
            }

            override fun onStart() {
                isLoading = true
                getView()?.showLoadingDialog("验证码发送中")
            }

            override fun onFinish() {
                isLoading = false
                getView()?.dismissLoadingDialog()
            }
        })
    }
    override fun createModel(): ChangePayPwContract.Model {
        return ChangePayPwModel()
    }
}
