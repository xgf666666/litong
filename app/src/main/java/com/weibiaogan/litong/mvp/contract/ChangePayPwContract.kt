package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改支付密码
 */
interface ChangePayPwContract {
    interface View : BaseMvpView {
        fun getNewPassword(): String
        fun getCheckPassword(): String
        fun getCode(): String
        fun setSendBtnEnable(b: Boolean)
        fun setSendBtnText(s: String)
        fun getphone(): String?
        fun changeSuccess()
    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {
        abstract   fun getData()
        abstract    fun changePw()
        abstract   fun sendVCode()

    }

    interface Model {
        fun getData(page: Int, httpObserver: XxBaseHttpObserver<List<Any>>)
        fun sendVCode(phone: String, xxBaseHttpObserver: XxBaseHttpObserver<Any>)
        fun settingAndChangePayPwd(userId : String,token : String,code : String,balance_payment : String,
                                   xxBaseHttpObserver: XxBaseHttpObserver<Any>)
    }
}
