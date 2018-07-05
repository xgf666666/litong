package com.weibiaogan.litong.ui.login

import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.ForgetPwContract
import com.weibiaogan.litong.mvp.presenter.ForgetPwPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_forgetpw.*

/**
 * author: Gubr
 * date: 2018/5/8
 * describe:忘记密码
 */
class ForgetPWActivity : BaseMvpActivity< ForgetPwPresenter>(), ForgetPwContract.View {



    override fun createPresenter(): ForgetPwPresenter = ForgetPwPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_forgetpw

    override fun initData() {
    }

    override fun initEvent() {
        bt_next.setOnClickListener { getPresenter().changePw() }
        tv_get_code.setOnClickListener { getPresenter().sendCode() }

    }

    override fun getPhone(): String = et_cellPhone.text.toString().trim()

    override fun getCode(): String = et_code.text.toString().trim()

    override fun getNewPassword(): String = et_new_password.text.toString().trim()

    override fun getCheckPassword(): String = et_check_password.text.toString().trim()


    override fun setSendBtnEnable(b: Boolean) {
        tv_get_code.isEnabled=b
    }

    override fun setSendBtnText(str: String) {
        tv_get_code.text = str
    }

    override fun changeSuccess() {
        finish()
    }

}