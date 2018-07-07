package com.weibiaogan.litong.ui.mine

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.umeng.socialize.utils.DeviceConfig.context
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.mine.MyPublishProjectAdatper
import com.weibiaogan.litong.mvp.contract.MyPublishProjectContract
import com.weibiaogan.litong.mvp.presenter.MyPublishProjectPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_my_publish_project.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:我的发布
 */
class MyPublishProjectActivity : BaseMvpActivity<MyPublishProjectPresenter>(),MyPublishProjectContract.View, SwipeRefreshLayout.OnRefreshListener {
        private var myPublishProjectAdatper : MyPublishProjectAdatper?=null

    /**
     * 初始化事件
     */
    override fun initEvent() {

    }
    /**
     * 刷新
     */
    override fun onRefresh() {
    }
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): MyPublishProjectPresenter=MyPublishProjectPresenter()


    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_my_publish_project

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_ing.setOnClickListener{startActivity(SureWorkerActivity::class.java)}
        recyclerView.layoutManager= LinearLayoutManager(context)
        myPublishProjectAdatper= MyPublishProjectAdatper()
        recyclerView.adapter=myPublishProjectAdatper
        swiperefreshlayout.setOnRefreshListener(this)
    }


}
