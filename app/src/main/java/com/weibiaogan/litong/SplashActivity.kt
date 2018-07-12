package com.weibiaogan.litong

import android.Manifest
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import com.blankj.utilcode.util.PermissionUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.ui.login.LoginActivity

import com.xx.baseuilibrary.mvp.BaseMvpViewActivity
import java.util.*


/**
 * SplashActivity

 */
class SplashActivity : BaseMvpViewActivity() {

//    需要检测的定位权限
    private var needPermissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                                          Manifest.permission.ACCESS_FINE_LOCATION,/*
                                          Manifest.permission.WRITE_EXTERNAL_STORAGE,*/
                                          /*Manifest.permission.READ_EXTERNAL_STORAGE,*/
                                          Manifest.permission.READ_PHONE_STATE,
                                          Manifest.permission.CHANGE_WIFI_STATE)

    override fun getActivityLayoutId(): Int = R.layout.activity_splash

    override fun initEvent() {
        PermissionUtils.permission(needPermissions[0] ,needPermissions[1] ,needPermissions[2]
                , needPermissions[3]/*, needPermissions[4]*//*, needPermissions[5]*/)
                .callback(object : PermissionUtils.SimpleCallback{
                    override fun onGranted() {
                        Log.e("Tag","有权限")
                        Timer().schedule(
                                object : TimerTask() {
                                    override fun run() {
                                        //延迟之后关闭当前页面
                                        if (Constants.isFirst()) {
                                            //startActivityThenFinishSelf(GuideActivity::class.java) 第一次进入app 向导页
                                            val intent = Intent(mContext, MainActivity::class.java)
                                            startActivity(intent)

                                        } else {
                                            if (!Constants.isLogin()){
                                                startActivity(Intent(mContext,LoginActivity::class.java))
                                            }else{
                                                val intent = Intent(mContext, MainActivity::class.java)
                                                startActivity(intent)
                                            }
                                            finish()
                                            overridePendingTransition(R.anim.scale_in,R.anim.scale_out)
                                        }
                                    }
                                },
                                1500
                        )
                    }

                    override fun onDenied() {
                        Log.e("Tag","没权限")
                        //被拒绝
                       /* showToast("拒绝给予权限会导致该定位不能正常使用")*/
                        Timer().schedule(
                                object : TimerTask() {
                                    override fun run() =//延迟之后关闭当前页面
                                            if (Constants.isFirst()) {
//                                                startActivityThenFinishSelf(GuideActivity::class.java)
                                            } else {
                                                val intent = Intent(mContext, MainActivity::class.java)
                                                startActivity(intent)
                                                finish()
                                                overridePendingTransition(R.anim.scale_in,R.anim.scale_out)
                                            }
                                },
                                1500
                        )
                    }

                })
                .rationale { it.again(false) }
                .request()
    }

    override fun initData() {
//        startActivity(LoginActivity::class.java)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //禁用返回键
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}