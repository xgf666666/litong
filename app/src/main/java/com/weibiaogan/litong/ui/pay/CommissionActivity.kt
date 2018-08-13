package com.weibiaogan.litong.ui.pay

import android.app.AlertDialog
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.extensions.format
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.CommissionContract
import com.weibiaogan.litong.mvp.presenter.CommissionPresenter
import com.weibiaogan.litong.widget.PwdEditText
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_commission.*

/**
 * author: Gubr
 * date: 2018/5/9
 * describe:佣金余额
 */
class CommissionActivity : BaseMvpActivity<CommissionPresenter>(), CommissionContract.View{
    override fun getData(data: String) {
        tv_content.text="最低提现为10.00，"+data
    }


    override fun successful() {
        var cash=et_cash.text.toString()
        Constants.getUserData().user.balance= Constants.getUserData().user.balance-cash.toDouble()
        startActivity(CommissionSuccessfulActivity::class.java)
        finish()
    }

    private var userData: UserCenterBean? = null


    override fun createPresenter(): CommissionPresenter {
        return CommissionPresenter()
    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_commission
    }

    override fun initData() {
        userData = Constants.getUserData()
        if (userData != null) {
            tv_balance.text = "${userData?.user?.balance?.format(2) ?: "0.00"}"
        }
        getPresenter().getData()
    }

    override fun initEvent() {
        tv_all_balance.setOnClickListener {
            et_cash.setText(userData?.user?.balance.toString())
        }

        bt_submit.setOnClickListener {
           showDialog()
        }
    }


    private fun commission(password: String) {
        val cash = et_cash.text.toString()

        if (TextUtils.isEmpty(cash)) {
            toast("请输入提现金额")
            return
        }

        val cashDouble = cash.toDouble()

        if ((cashDouble < 5) || (cashDouble > userData?.user?.balance ?: 0.0)) {
            toast("请输入正确金额")
            return
        }


        val account = et_account.text.toString()
        if (TextUtils.isEmpty(account) || account.length < 5) {
            toast("请输入正确帐号")
            return
        }


        val balancePayment = password

        if (TextUtils.isEmpty(balancePayment)) {
            toast("请输入密码")
            return
        }


        getPresenter().commission(cash, account, "1", balancePayment)
    }
    var password:String?=null
    var dialog:AlertDialog?=null
    //支付框
    fun showDialog() {
        var view = View.inflate(mContext, R.layout.view_input_pay_psw_dialog, null)
        var psw_view=view.findViewById<PwdEditText>(R.id.psw_view)
        var tv_forget_pwd=view.findViewById<TextView>(R.id.tv_forget_pwd)
        var bt_quxiao=view.findViewById<TextView>(R.id.bt_quxiao)
        var bt_sure=view.findViewById<TextView>(R.id.bt_sure)
        bt_quxiao.setOnClickListener{
            dialog!!.dismiss()
        }
        bt_sure.setOnClickListener{
            if (password!=null&&password?.length==6){
                commission(password!!)
            }

        }
        tv_forget_pwd.setOnClickListener{
                startActivity(ChangePayPwActivity::class.java)
        }
        psw_view.setOnInputFinishListener{
            password=it
        }
        dialog = AlertDialog.Builder(mContext).create()
        dialog!!.setView(view)
//        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.show()
    }

}