package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import android.util.Log
import com.weibiaogan.litong.common.md5Salt
import com.weibiaogan.litong.mvp.contract.ForgetPwContract
import com.weibiaogan.litong.mvp.model.ForgetPwModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DefaultObserver
import java.util.concurrent.TimeUnit

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:忘记密码
 */
class ForgetPwPresenter :  ForgetPwContract.Presenter() {


    override fun createModel(): ForgetPwContract.Model {
        return ForgetPwModel()
    }


    override fun sendCode() {
        val phone = getView()?.phone!!
        if (TextUtils.isEmpty(phone)||phone.length!=11) {
            getView()?.showToast("请输入正确的手机号")
            return
        }

        getModel().sendCode(phone,object:XxBaseHttpObserver<Any>(){
            override fun onCompleted(msg: String?, entity: Any?) {
                getView()?.showToast(msg)
                val timeCount=60
               Observable.interval(0,1,TimeUnit.SECONDS)
                       .take(timeCount+1.toLong())
                       .map { timeCount-it }
                       .doOnSubscribe { getView()?.setSendBtnEnable(false) }
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(object :DefaultObserver<Long>(){
                           override fun onNext(value: Long) {
                               getView()?.setSendBtnText("($value)")

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
                isLoading=true
                getView()?.showLoadingDialog("验证码发送中")
            }

            override fun onFinish() {
                isLoading=false
                getView()?.dismissLoadingDialog()
            }
        })
    }


    override fun changePw() {
        if (getView() == null)
            return
        val phone = getView()!!.phone
        val vCode = getView()!!.code
        val newPw = getView()!!.newPassword
        val surePw = getView()!!.checkPassword
        Log.e("Tag","newPw="+newPw+"surePw="+surePw)
        if (TextUtils.isEmpty(phone) || phone.length != 11) {
            getView()?.showToast("请输入正确的手机号")
            return
        }
        if (TextUtils.isEmpty(newPw) || newPw.length < 6) {
            getView()?.showToast("请输入密码,至少6位")
            return
        }
        if (TextUtils.isEmpty(surePw) || surePw.length < 6) {
            getView()?.showToast("请确认密码,至少6位")
            return
        }
        if (newPw != surePw) {
            getView()?.showToast("两次输入密码不一致")
            return
        }
        if (TextUtils.isEmpty(vCode) || vCode.length != 6) {
            getView()?.showToast("请输入完整的验证码")
            return
        }
        val encryptMD5ToString = newPw.md5Salt()
        getModel().changePw(phone, vCode, encryptMD5ToString, object : XxBaseHttpObserver<Any>() {
            override fun onCompleted(msg: String?, entity: Any?) {
                getView()?.showToast(msg)
                getView()?.changeSuccess()
            }

            override fun onError(error: String?) {
                getView()?.showToast(error)
            }

            override fun onStart() {
                isLoading = true
                getView()?.showLoadingDialog()
            }

            override fun onFinish() {
                isLoading = false
                getView()?.dismissLoadingDialog()
            }
        })
    }
}
