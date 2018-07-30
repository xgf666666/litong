package com.weibiaogan.litong.ui.login

import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import com.tencent.tauth.Tencent
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA
import com.weibiaogan.litong.App
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.LoginConstract
import com.weibiaogan.litong.mvp.presenter.LoginPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.blankj.utilcode.util.ActivityUtils
import com.umeng.socialize.UMAuthListener



/**
 * author: Gubr
 * date: 2018/5/6
 * describe:
 */
class LoginActivity:BaseMvpActivity<LoginPresenter>(),LoginConstract.View {

    var mType = 1   // qq : 1 wx : 2
    var mOpenId = ""

    override fun createPresenter(): LoginPresenter = LoginPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_login

    override fun initData() {

    }

    override fun initEvent() {
        tv_register.setOnClickListener { startActivity(RegisterActivity::class.java) }
        tv_forgetPassword.setOnClickListener { startActivity(ForgetPWActivity::class.java) }
        bt_login.setOnClickListener { getPresenter().login() }
        iv_qq.setOnClickListener { login(0) }
        iv_wechat.setOnClickListener { login(1) }
        ib_backs.setOnClickListener {
            ActivityUtils.finishAllActivities(true)
        }
        iv_icon.setOnClickListener { startActivity(MainActivity::class.java) }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            ActivityUtils.finishAllActivities(true)
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun getMobile(): String {
        return et_cellPhone.text.trim().toString()
    }

    override fun getPassword(): String {
        return et_code.text.trim().toString()
    }

    override fun loginSuccess() {
        ActivityUtils.finishAllActivities()
        startActivity(MainActivity::class.java)
        finish()

    }

    override fun loginThreeInfo() {
        var intent = Intent(this,RegisterActivity::class.java)
        intent.putExtra("register_type",mType)
        intent.putExtra("register_openid",mOpenId)
        startActivity(intent)
        //finish()
    }

    fun login(type : Int){
        //showLoadingDialog("")
        if (type == 0){
            mType = 1
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener)
        }else{
            mType = 2
            UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }

    var authListener: UMAuthListener = object : UMAuthListener {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        override fun onStart(platform: SHARE_MEDIA) {
            //Toast.makeText(mContext, "开始", Toast.LENGTH_LONG).show()
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        override fun onComplete(platform: SHARE_MEDIA, action: Int, data: Map<String, String>) {
            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show()
            //dismissLoadingDialog()
            mOpenId = data["openid"]!!
            getPresenter().loginThree(mType.toString(), mOpenId)
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        override fun onError(platform: SHARE_MEDIA, action: Int, t: Throwable) {
            //dismissLoadingDialog()
            Toast.makeText(mContext, "失败：" + t.message, Toast.LENGTH_LONG).show()
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        override fun onCancel(platform: SHARE_MEDIA, action: Int) {
            //dismissLoadingDialog()
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show()
        }
    }

}