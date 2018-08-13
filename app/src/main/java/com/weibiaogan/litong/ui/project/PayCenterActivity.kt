package com.weibiaogan.litong.ui.project

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.ActivityUtils
import com.google.gson.Gson
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.weibiaogan.litong.App
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.MemberBean
import com.weibiaogan.litong.entity.PayBean
import com.weibiaogan.litong.entity.PayResult
import com.weibiaogan.litong.mvp.contract.PayCenterConstract
import com.weibiaogan.litong.mvp.presenter.PayCenterPresenter
import com.weibiaogan.litong.ui.mine.KnowMemberActivity
import com.weibiaogan.litong.ui.mine.MyPublishProjectActivity
import com.xx.anypay.XxAnyPay
import com.xx.anypay.XxAnyPayResultCallBack
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_pay_center.*
import java.net.URLEncoder

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:支付中心界面
 */


class PayCenterActivity : BaseMvpActivity<PayCenterPresenter>(),PayCenterConstract.View, CompoundButton.OnCheckedChangeListener {

//    private var mhandler: Handler = object : Handler() {
//       override fun handleMessage(msg: Message?) {
//           super.handleMessage(msg)
//           if (msg?.what==1){
//               Log.i("alipaysssss","支付宝调用3")
//               var map=msg.obj as Map<String,String>
//               var payResult= PayResult(map)
//               var info=payResult.result
//               var result= URLEncoder.encode(info,"utf-8")
//               var resultStatus=payResult.resultStatus
//
//               Log.i("resultStatus",resultStatus+payResult.memo+"ddd"+result)
//               if (resultStatus.equals("9000")){
//                   showToast("支付成功")
////                   App.getInstance()?.cleanListActivity()
//                   ActivityUtils.finishActivity(MyPublishProjectActivity::class.java)
//                   finish()
//
//               }
//           }
//       }
//
//
//
//   }

    override fun payResult(payBean: PayBean) {
        //微信支付
//        if (isPayTpye.equals("wechat")){
//            var api= WXAPIFactory.createWXAPI(mActivity, null)
//            api.registerApp(payBean.data.appid)
//            var request= PayReq()
//            request.appId=payBean.data.appid
//            request.partnerId=payBean.data.mch_id
//            request.prepayId=payBean.data.prepay_id
//            request.packageValue="Sign=WXPay"
//             request.nonceStr=payBean.data.nonce_str
//            request.timeStamp=""+payBean.data.time
//            request.sign=payBean.data.sign
//            api.sendReq(request)
////            finish()
//        }else if(isPayTpye.equals("alipay")){
//            Log.i("alipaysssss","支付宝调用")
//            setAliPay(payBean)//支付宝支付
//        }
        dismissLoadingDialog()
        XxAnyPay.intance
                .openAnyPay(if (isPayTpye == "wechat") XxAnyPay.XXPAY_WX else XxAnyPay.XXPAY_ALI,if (isPayTpye == "wechat") Gson().toJson(payBean.data) else payBean.data.sign, object : XxAnyPayResultCallBack {
                    override fun onPayFiale(error: String) {
                        showToast(error)
                    }

                    override fun onPaySuccess() {
                        showToast("支付成功")
                        ActivityUtils.finishActivity(MyPublishProjectActivity::class.java)
                        finish()
                    }
    })
    }

    companion object {
        var mActivity : Context? = null
        fun startPayCenter(context: Context,flag : String,ptId:String,money:String){
            mActivity = context
            val intent=Intent(context,PayCenterActivity::class.java)
            intent.putExtra("FLAG",flag)
            intent.putExtra("ptId",ptId)
            intent.putExtra("money",money)
            context.startActivity(intent)
        }
    }
    override fun setView(memberBean: MemberBean) {

        tv_price.setText("￥"+memberBean.system_content)
    }

    override fun initEvent() {
        iv_going.setOnClickListener{startActivity(KnowMemberActivity::class.java)}
        cb_weixin.setOnCheckedChangeListener(this)
        bt_zhifu.setOnClickListener{
            if (isPayTpye.isNullOrEmpty()){
                showToast("请选择支付方式")
            }else{        ib_back.setOnClickListener{finish()}

                getPresenter().pay(ptId,isPayTpye,flag)
                showLoadingDialog()
            }
        }
        cb_zhifubao.setOnCheckedChangeListener(this)
    }
    //1是保证金金额 2是支付一期款 3是支付二期款 4是支付三期款 5是会员充值
    var flag:String=""
    var ptId:String=""
    var money:String=""
    override fun initData() {
        XxAnyPay.intance.init(this)
          flag=intent.getStringExtra("FLAG")?:""
        ptId=intent.getStringExtra("ptId")?:""
        money=intent.getStringExtra("money")?:""
        when (flag){
            "1"->{
                iv_going.visibility= View.GONE
                tv_price.setText(money)
            }
            "2"->{
                iv_going.visibility= View.GONE
                tv_content.setText("已经确认工人，请尽快支付首款")
                tv_price.setText(money)
            }
            "3"->{
                iv_going.visibility= View.GONE
                tv_content.setText("请尽快支付二期款")
                tv_price.setText(money)
            }
            "4"->{
                iv_going.visibility= View.GONE
                tv_content.setText("请尽快支付尾款")
                tv_price.setText(money)
            }
            "5"->{
                rl_title.setBackgroundColor(Color.parseColor("#ffad1d"))
                tv_content.setTextColor(Color.parseColor("#ffffff"))
                tv_content.setText("了解会员特权")
                iv_going.setOnClickListener{startActivity(KnowMemberActivity::class.java)}
                getPresenter().vip(Constants.getToken().user_id.toString(),Constants.getToken().token)
            }


        }
    }
    //判断是选择微信还是支付宝
    var isPayTpye:String="wechat"

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked){
            when(buttonView?.id){
                R.id.cb_weixin->{
                    cb_zhifubao.isChecked=false
                    isPayTpye="wechat"
                }
                R.id.cb_zhifubao->{
                    cb_weixin.isChecked=false
                    isPayTpye="alipay"
                }


            }
        }

    }

    override fun getActivityLayoutId(): Int =R.layout.activity_pay_center

    override fun createPresenter(): PayCenterPresenter =PayCenterPresenter()
//    private fun setAliPay(payBean: PayBean){
//        var runnable:Runnable= object : Runnable {
//            override fun run() {
//                var alipay= PayTask(this@PayCenterActivity)
//                var result=alipay.payV2(payBean.data.sign,true)
//                var message=Message()
//                message.what=1
//                message.obj=result
//                mhandler.sendMessage(message)
//                Log.i("alipaysssss","支付宝"+payBean.data.sign)
//            }
//        }
//        var thread=Thread(runnable)
//        thread.start()
//
//    }


}
