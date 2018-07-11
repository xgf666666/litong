package com.weibiaogan.litong.ui.project

import com.weibiaogan.litong.R
import com.weibiaogan.litong.ui.orders.OrdersFragment
import com.xx.baseuilibrary.mvp.BaseMvpViewActivity

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:  找项目，项目列表 ，我要接单
 */
class ProjectListActivity : BaseMvpViewActivity() {
    override fun getActivityLayoutId(): Int = R.layout.activity_project

    override fun initData() {
        var fragment = OrdersFragment()
        supportFragmentManager.beginTransaction().add(R.id.fl_project_content,fragment).show(fragment).commit()
    }

    override fun initEvent() {
    }
}