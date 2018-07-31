package com.weibiaogan.litong.ui.modify

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
import kotlinx.android.synthetic.main.activity_modifybind1.*
import kotlinx.android.synthetic.main.home_item_two_view.*
import java.util.concurrent.TimeUnit

/**
 * author: Gubr
 * date: 2018/5/23
 * describe:
 */
class ModifyBindFragment1 : BaseMvpViewFragment() {
    private var isLoading = false
    private var phone: String? = null

    override fun getFragmentLayoutId(): Int {
        return R.layout.activity_modifybind1
    }

    override fun initView(view: View?) {

    }

    override fun initEvent(view: View?) {
        phone=Constants.getPhone()
        et_cellPhone.setText(phone)
        ib_back.setOnClickListener { finishActivity() }
        bt_submit.setOnClickListener { checkCode(et_code.text.toString().trim()) }
        tv_get_code.setOnClickListener { sendVCode(et_cellPhone.text.toString().trim()) }
    }

    override fun initData() {
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
        if (tv_get_code!=null)
        tv_get_code.setText(s)
    }

    private fun setSendBtnEnable(b: Boolean) {
        tv_get_code.isEnabled = b
    }

    private fun checkCode(code: String) {
        if (TextUtils.isEmpty(et_code.text.toString())){
            toast("请填写认证码")

        }else {
            if (Constants.isLogin()) {
                val userId = Constants.getToken().user_id.toString()
                val token = Constants.getToken().token
//            if (code.length != 6) {
//                toast("验证码格式出错")
//                return
//            }
                AppApi.Api().updateBeforeUserPhone(userId, token, code).ui(
                        {
                            et_cellPhone.setText("")
                            et_code.setText("")
                            (activity as ModifyBindActivity).showFragment(1)
                        }, {
                    toast("验证码出错")
                })
            }
        }
    }


}