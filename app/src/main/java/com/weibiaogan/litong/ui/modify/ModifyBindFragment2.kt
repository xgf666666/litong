package com.weibiaogan.litong.ui.modify

import android.view.View
import com.blankj.utilcode.util.RegexUtils
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.extensions.toast
import com.xx.baseuilibrary.mvp.BaseMvpViewFragment
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DefaultObserver


import kotlinx.android.synthetic.main.activity_modifybind2.*
import java.util.concurrent.TimeUnit

/**
 * author: Gubr
 * date: 2018/5/23
 * describe:
 */
class ModifyBindFragment2 : BaseMvpViewFragment() {

    private var isLoading=false

    override fun getFragmentLayoutId(): Int {
        return R.layout.activity_modifybind2
    }

    override fun initView(view: View?) {

    }

    override fun initEvent(view: View?) {
        ib_back.setOnClickListener {
//            et_cellPhone.setText("")
//            et_code.setText("")
//            (activity as ModifyBindActivity).showFragment(0)
            finishActivity()
        }
        bt_submit.setOnClickListener { checkCode(et_code.text.toString().trim(),et_cellPhone.text.toString().trim()) }
        tv_get_code.setOnClickListener { sendVCode(et_cellPhone.text.toString().trim()) }


    }


    private fun checkCode(code: String,phone:String) {

        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token

            if (!RegexUtils.isMobileSimple(phone)){
                toast("手机号码出错")
                return
            }


//            if (code.length != 6) {
//                toast("验证码格式出错")
//                return
//            }
            AppApi.Api().updateUserPhone(userId, token,phone, code).ui(
                    {
                        toast(it.msg+"")
                        Constants.putPhone(phone)
                     finishActivity()
                    }, {
                toast(""+it.message)
            })
        }
    }



    fun sendVCode(phone: String) {

        if (!RegexUtils.isMobileSimple(phone)) {
            showToast("请输入正确的手机号")
            return
        }

        AppApi.Api().sendCode(phone).compose(RxHelper.io_main()).subscribe(object : XxBaseHttpObserver<Any>() {
            override fun onCompleted(msg: String?, entity: Any?) {
                showToast(msg)
                //开始倒计时
                val timeCount = 60
                Observable.interval(0, 1, TimeUnit.SECONDS)
                        .take((timeCount + 1).toLong())
                        .map { aLong -> timeCount - aLong!! }
                        .doOnSubscribe {
                            //发送数据中,将发送按钮状态设置为不可用
                            setSendBtnEnable(false)

                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : DefaultObserver<Long>() {
                            override fun onNext(value: Long) {
                                setSendBtnText("(" + value + "s)")
                            }

                            override fun onError(e: Throwable) {
                                setSendBtnEnable(true)
                                setSendBtnText("重试")
                            }

                            override fun onComplete() {
                                setSendBtnEnable(true)
                                setSendBtnText("重新发送")
                            }
                        })
            }

            override fun onError(error: String?) {
                showToast(error)
            }

            override fun onStart() {
                isLoading = true
                showLoadingDialog("验证码发送中")
            }

            override fun onFinish() {
                isLoading = false
                dismissLoadingDialog()
            }
        }

        )

    }


    private fun setSendBtnText(s: String) {
        if(tv_get_code!=null)
        tv_get_code.setText(s)
    }

    private fun setSendBtnEnable(b: Boolean) {
        tv_get_code.isEnabled = b
    }

    override fun initData() {
    }

}