package com.weibiaogan.litong.ui.pay

import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.mvp.contract.ChangePayPwContract
import com.weibiaogan.litong.mvp.presenter.ChangePayPwPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_change_pw2.*

/**
 * author: Gubr
 * date: 2018/5/8
 * describe:
 */
class ChangePayPwActivity:BaseMvpActivity<ChangePayPwPresenter>(),ChangePayPwContract.View{
    override fun createPresenter(): ChangePayPwPresenter {
        return ChangePayPwPresenter()
    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_change_pay_pw2
    }

    private var phone: String? = null

    override fun initData() {

        phone=Constants.getPhone()
        tv_phone.setText(phone?.replaceRange(4,7,"****"))

    }

    override fun initEvent() {
        bt_submit.setOnClickListener { getPresenter().changePw() }
        tv_get_code.setOnClickListener { getPresenter().sendVCode() }
    }


    override fun getNewPassword(): String = et_new_password.text.toString().trim()

    override fun getCheckPassword(): String = et_check_password.toString().trim()

    override fun getCode(): String {
        return et_code.text.toString().trim()
    }

    override fun setSendBtnEnable(b: Boolean) {
        tv_get_code.isEnabled = b
    }

    override fun setSendBtnText(s: String) {
        tv_get_code.setText(s)
    }

    override fun getphone(): String? {
        return phone
    }


    override fun changeSuccess() {
        finish()
    }

}