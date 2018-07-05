package com.weibiaogan.litong.ui.pay

import android.text.TextUtils
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.extensions.format
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.CommissionContract
import com.weibiaogan.litong.mvp.presenter.CommissionPresenter
import com.weibiaogan.litong.widget.InputPwdDialog
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_commission.*

/**
 * author: Gubr
 * date: 2018/5/9
 * describe:佣金余额
 */
class CommissionActivity : BaseMvpActivity<CommissionPresenter>(), CommissionContract.View {


    override fun successful() {
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
    }

    override fun initEvent() {
        tv_all_balance.setOnClickListener {
            et_cash.setText(userData?.user?.balance.toString())
        }

        bt_submit.setOnClickListener {
            onShowPayPwdDialog()
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
            toast("请输入帐号")
            return
        }


        val balancePayment = password

        if (TextUtils.isEmpty(balancePayment)) {
            toast("请输入密码")
            return
        }


        getPresenter().commission(cash, account, "1", balancePayment)
    }


    fun onShowPayPwdDialog() {
        val inputPwdDialog = InputPwdDialog(mContext)
        inputPwdDialog.setCancelable(false)
        inputPwdDialog.setOnForgetClickListener { startActivity(ChangePayPwActivity::class.java) }
        inputPwdDialog.setOnPswDialogClickListener(object : InputPwdDialog.OnPswDialogClickListener {
            override fun onConfirm(password: String?) {
                commission(password ?: "")
                inputPwdDialog.dismiss()
            }

            override fun onLinkClick() {
            }

        })
        inputPwdDialog.show()
    }

}