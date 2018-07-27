package com.weibiaogan.litong.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.weibiaogan.litong.App
import com.weibiaogan.litong.R
import com.xx.anypay.wxapi.WxPayEntryActivity
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory


//
class WXPayEntryActivity :WxPayEntryActivity(){
//        Activity(), IWXAPIEventHandler {
//    private var api: IWXAPI? = null
//
//    override fun onResp(resp: BaseResp?) {
//        when(resp?.errCode){
//            0->{            Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show()
//            }
//            -1->{
//                Toast.makeText(this,"支付失败",Toast.LENGTH_SHORT).show()
//
//            }
//            1->{            Toast.makeText(this,"支付取消",Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
//
//    override fun onReq(p0: BaseReq?) {
//        Log.i("dfgsa","我是req")
////        App.getInstance()?.cleanListActivity()
////        finish(
//    }
//
//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        setIntent(intent)
//        api?.handleIntent(intent, this)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main2)
//        api=WXAPIFactory.createWXAPI(this,"wx4fc9ff6e5c76f8bc")
//        api?.handleIntent(getIntent(), this)
//    }
}
