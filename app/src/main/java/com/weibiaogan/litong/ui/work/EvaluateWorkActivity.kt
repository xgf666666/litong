package com.weibiaogan.litong.ui.work

import com.weibiaogan.litong.R
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_evaluate_work.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class EvaluateWorkActivity : BaseActivity() {
    override fun initData() {
        tv_evaluate_title.setText(resources.getText(R.string.evaluate_work_title))
    }

    override fun initEvent() {
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_evaluate_work
}