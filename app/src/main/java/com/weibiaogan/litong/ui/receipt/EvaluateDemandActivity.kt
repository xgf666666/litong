package com.weibiaogan.litong.ui.receipt

import com.weibiaogan.litong.R
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_evaluate_work.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe: 评论需求方
 */
class EvaluateDemandActivity : BaseActivity() {
    override fun getActivityLayoutId(): Int = R.layout.activity_evaluate_work

    override fun initData() {
        tv_evaluate_title.setText(resources.getText(R.string.evaluate_demand_title))
    }

    override fun initEvent() {
    }
}