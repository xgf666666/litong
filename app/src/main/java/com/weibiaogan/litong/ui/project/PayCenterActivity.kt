package com.weibiaogan.litong.ui.project

import android.app.Activity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.PayCenterConstract
import com.weibiaogan.litong.mvp.presenter.PayCenterPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_pay_center.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:支付中心界面
 */


class PayCenterActivity : BaseMvpActivity<PayCenterPresenter>(),PayCenterConstract.View {
    override fun initEvent() {
        ib_back.setOnClickListener{finish()}
    }

    override fun initData() {
    }

    override fun getActivityLayoutId(): Int =R.layout.activity_pay_center

    override fun createPresenter(): PayCenterPresenter =PayCenterPresenter()


}
