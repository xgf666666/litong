package com.weibiaogan.litong.ui.project

import com.weibiaogan.litong.R
import com.weibiaogan.litong.ui.orders.OrdersFragment
import com.xx.baseuilibrary.BaseActivity

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class ProjectListActivity : BaseActivity() {
    override fun getActivityLayoutId(): Int = R.layout.activity_project

    override fun initData() {
        var fragment = OrdersFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_project_content,fragment).show(fragment).commit()
    }

    override fun initEvent() {
    }
}