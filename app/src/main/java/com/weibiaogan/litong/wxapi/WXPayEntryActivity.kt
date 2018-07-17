package com.weibiaogan.litong.wxapi

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.weibiaogan.litong.R

class WXPayEntryActivity : Activity(), IWXAPIEventHandler {
    override fun onResp(resp: BaseResp?) {
        if (resp?.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.i("dfgsa","我是resp")
        }
    }

    override fun onReq(p0: BaseReq?) {
        Log.i("dfgsa","我是req")
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
    }
}
