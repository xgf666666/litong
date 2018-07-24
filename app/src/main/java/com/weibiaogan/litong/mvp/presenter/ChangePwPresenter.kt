package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import android.util.Log
import com.blankj.utilcode.util.RegexUtils
import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.common.md5Salt
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.ChangePwContract
import com.weibiaogan.litong.mvp.model.ChangePwModel
import com.weibiaogan.litong.utils.showToast
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DefaultObserver
import java.util.concurrent.TimeUnit

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改密码
 */
class ChangePwPresenter : ChangePwContract.Presenter() {
    override fun changePw() {
        val code: String? = getView()?.code

        if (TextUtils.isEmpty(code)) {
            getView()?.showToast("请输入验证码")
            return
        }

        val checkPassword = getView()?.checkPassword

        val newPassword = getView()?.newPassword

        if (newPassword?.length ?: 0 > 6||newPassword?.length ?: 0<=16) {
            if (newPassword?.equals(checkPassword) == true) {
                getView()?.showToast("新密码两次输入不一样")
                return
            }
        }else{
            getView()?.showToast("请输入6到16位密码")
        }

        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token

        val encryptMD5ToString = newPassword?.md5Salt()

        getModel().forgetUserPwd(userId, token, code, encryptMD5ToString ).loadDefulat(getView()!!)
                .ui({
                    Log.i("tttttttttttt",it.msg)
                    getView()?.changeSuccess()
                }, {
                    getView()?.showToast(it)
                })


    }

        //发送验证码
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

    override fun createModel(): ChangePwContract.Model {
        return ChangePwModel()
    }
}
