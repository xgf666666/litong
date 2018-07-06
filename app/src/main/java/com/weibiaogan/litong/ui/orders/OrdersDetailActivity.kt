package com.weibiaogan.litong.ui.orders

import android.app.Activity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.model.OrderdDetailModel
import com.weibiaogan.litong.mvp.presenter.OrderDetailPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity

/**
 * author: xiaoguangfei
 * date: 2018/7/5
 * describe:项目详情
 */
class OrdersDetailActivity : BaseMvpActivity<OrderDetailPresenter>(),OrderdDetailModel.View {
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): OrderDetailPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_orders_detail

    /**
     * 初始化数据状态
     */
    override fun initData() {
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }


}
