package com.weibiaogan.litong.ui.project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.MemberBean
import com.weibiaogan.litong.mvp.contract.PayCenterConstract
import com.weibiaogan.litong.mvp.presenter.PayCenterPresenter
import com.weibiaogan.litong.ui.mine.KnowMemberActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_pay_center.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:支付中心界面
 */


class PayCenterActivity : BaseMvpActivity<PayCenterPresenter>(),PayCenterConstract.View {

    companion object {
        fun startPayCenter(context: Context,flag : String){
            val intent=Intent(context,PayCenterActivity::class.java)
            intent.putExtra("FLAG",flag)
            context.startActivity(intent)
        }
    }
    override fun setView(memberBean: MemberBean) {

        tv_price.setText("￥"+memberBean.system_content)
    }

    private var ISVIP:String="1"//会员支付页面
    override fun initEvent() {
        ib_back.setOnClickListener{finish()}
        iv_going.setOnClickListener{startActivity(KnowMemberActivity::class.java)}
    }
    override fun initData() {
         var flag:String=intent.getStringExtra("FLAG")?:""
        if (flag.equals(ISVIP)){
            rl_title.setBackgroundColor(Color.parseColor("#ffad1d"))
            tv_content.setTextColor(Color.parseColor("#ffffff"))
            tv_content.setText("了解会员特权")
            iv_going.setOnClickListener{startActivity(KnowMemberActivity::class.java)}
            getPresenter().vip(Constants.getToken().user_id.toString(),Constants.getToken().token)
        }
    }
    override fun getActivityLayoutId(): Int =R.layout.activity_pay_center

    override fun createPresenter(): PayCenterPresenter =PayCenterPresenter()
//    companion object {
//        fun startActivity(context: Context){
//            Intent
//        }
//    }

}
