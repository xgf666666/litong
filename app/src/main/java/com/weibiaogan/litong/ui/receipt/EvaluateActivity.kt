package com.weibiaogan.litong.ui.receipt

import com.weibiaogan.litong.R
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_evaluate_work.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe: 评论需求方
 */
class EvaluateActivity : BaseActivity() {

    override fun getActivityLayoutId(): Int = R.layout.activity_evaluate_work

    override fun initData() {
        var type = intent.getIntExtra("type", 0)
        when(type){
            0 -> tv_evaluate_title.text = resources.getText(R.string.evaluate_work_title)
            1 -> tv_evaluate_title.text = resources.getText(R.string.evaluate_demand_title)
        }

    }

    override fun initEvent() {

    }
}