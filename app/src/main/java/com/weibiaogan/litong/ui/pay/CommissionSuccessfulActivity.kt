package com.weibiaogan.litong.ui.pay

import com.weibiaogan.litong.R
import com.xx.baseuilibrary.mvp.BaseMvpViewActivity
import kotlinx.android.synthetic.main.activity_commission.*

/**
 * author: Gubr
 * date: 2018/6/7
 * describe:
 */
class CommissionSuccessfulActivity:BaseMvpViewActivity(){
    override fun getActivityLayoutId(): Int {
        return R.layout.activity_commission_successful
    }

    override fun initData() {
    }

    override fun initEvent() {
        bt_submit.setOnClickListener { finish() }
    }

}