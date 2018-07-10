package com.weibiaogan.litong.ui.login

import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.ChangePwContract
import com.weibiaogan.litong.mvp.presenter.ChangePwPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_change_pw2.*


/**
 * author: Gubr
 * date: 2018/5/8
 * describe:忘记密码
 */
class ChangePWActivity : BaseMvpActivity<ChangePwPresenter>(), ChangePwContract.View {


    private var phone: String? = null


    override fun createPresenter(): ChangePwPresenter = ChangePwPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_change_pw2

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
        toast("修改成功")
        finish()
    }

}