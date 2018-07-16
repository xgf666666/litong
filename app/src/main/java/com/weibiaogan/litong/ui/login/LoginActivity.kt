package com.weibiaogan.litong.ui.login

import com.weibiaogan.litong.App
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.LoginConstract
import com.weibiaogan.litong.mvp.presenter.LoginPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * author: Gubr
 * date: 2018/5/6
 * describe:
 */
class LoginActivity:BaseMvpActivity<LoginPresenter>(),LoginConstract.View {

    override fun createPresenter(): LoginPresenter = LoginPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_login

    override fun initData() {

    }

    override fun initEvent() {
        tv_register.setOnClickListener { startActivity(RegisterActivity::class.java) }
        tv_forgetPassword.setOnClickListener { startActivity(ForgetPWActivity::class.java) }
        bt_login.setOnClickListener { getPresenter().login() }
        iv_qq.setOnClickListener { }
        iv_wechat.setOnClickListener { }
        ib_backs.setOnClickListener {
            App.getInstance()?.cleanActivity()
            finish()

        }
        iv_icon.setOnClickListener { startActivity(MainActivity::class.java) }
    }

    override fun getMobile(): String {
        return et_cellPhone.text.trim().toString()
    }

    override fun getPassword(): String {
        return et_code.text.trim().toString()
    }

    override fun loginSuccess() {
        startActivity(MainActivity::class.java)
        finish()
    }

}