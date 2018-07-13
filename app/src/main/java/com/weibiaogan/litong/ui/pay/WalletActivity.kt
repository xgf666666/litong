package com.weibiaogan.litong.ui.pay

import android.content.Intent
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.extensions.format
import com.weibiaogan.litong.mvp.contract.WalletContract
import com.weibiaogan.litong.mvp.presenter.WalletPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_wallet.*

/**
 * author: Gubr
 * date: 2018/5/9
 * describe:账户
 */
class WalletActivity:BaseMvpActivity<WalletPresenter>(),WalletContract.View{

    private lateinit var mUserData : UserCenterBean
    override fun createPresenter(): WalletPresenter {
        return WalletPresenter()
    }

    override fun getActivityLayoutId(): Int {
        return  R.layout.activity_wallet
    }

    private fun initView() {
//        tv_money.text="${mUserData.user.balance.format(2)}"
        setMoeny(mUserData.user.balance)

    }

    override fun initData() {
//        mUserData = Constants.getUserData()
//        initView()
    }

    override fun onResume() {
        super.onResume()
        mUserData = Constants.getUserData()
        initView()
    }



    override fun initEvent() {
        bt_submit.setOnClickListener {
            val intent = Intent(mContext, CommissionActivity::class.java)
            startActivity(intent)
//            startActivityForResult(intent,0)
        }
    }

    /**
     * 刷新余额
     */
    override fun onRefreshBalanceSuccess(entity: UserCenterBean) {
//        tv_money.text="${entity.user.balance.format(2)}"
        setMoeny(entity.user.balance)
    }


    fun setMoeny(money:Number) {
        tv_money.text="${money.format(2)}"
    }

}