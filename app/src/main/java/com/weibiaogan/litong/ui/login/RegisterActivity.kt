package com.weibiaogan.litong.ui.login

import android.os.Handler
import android.os.Message
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.R.id.*
import com.weibiaogan.litong.mvp.contract.RegisterContracct
import com.weibiaogan.litong.mvp.presenter.RegisterPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 * author: Gubr
 * date: 2018/5/9
 * describe:注册  绑定手机
 */
class RegisterActivity:BaseMvpActivity<RegisterPresenter>(),RegisterContracct.View{
    var mType = 0
    var openid = ""

    override fun createPresenter(): RegisterPresenter {
        return RegisterPresenter()
    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initData() {
        mType = intent.getIntExtra("register_type", 0)
        if (mType != 0){
            openid = intent.getStringExtra("register_openid")
            tv_register_title.text = "绑定手机"
            bt_submit.text = "确定绑定"
        }
    }

    override fun initEvent() {
        tv_get_code.setOnClickListener{
            getPresenter().sendCode()
        }
        bt_submit.setOnClickListener {
            if (mType != 0){
                getPresenter().regThree(mType.toString(),openid)
            }else{
                getPresenter().register()
            }
        }
    }


    override fun getPhone(): String {
        return et_phone.text.toString().trim()
    }

    override fun getCode(): String {
        return et_code.text.toString().trim()
    }

    override fun getNewPassword(): String {
        return et_new_password.text.toString().trim()
    }

    override fun getCheckPassword(): String {
        return et_check_password.text.toString().trim()
    }

    override fun getInvite(): String {
        return et_invite.text.toString().trim()
    }

    override fun onSendCodeSuccess(msg: String?) {
        mHandler.post(mRunnable)
    }

    override fun onRegisterSuccess(msg: String?) {
        finish()
    }

    override fun onBindSuccess() {
        startActivity(MainActivity::class.java)
        finish()
    }

    private var time = 60//验证码时间
    private var mHandler : Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            time--
            if (time > 0) {
                tv_get_code.setText(time.toString() + " s")
                tv_get_code.setEnabled(false)
            } else {
                this.removeCallbacks(mRunnable)
                tv_get_code.setText("重新获取")
                time = 60
                tv_get_code.setEnabled(true)
            }
        }
    }

    private val mRunnable = object : Runnable{
        override fun run() {
            mHandler.sendEmptyMessage(0)
            mHandler.postDelayed(this, 1000)
        }

    }

}