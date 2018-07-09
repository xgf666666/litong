package com.weibiaogan.litong.ui.project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.HistoryprojectContract
import com.weibiaogan.litong.mvp.presenter.HistoryprojectPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
/**
* author: xiaoguagnfei
* date: 2018/7/5
* describe:支付中心界面
*/
class HistoryProjectActivity : BaseMvpActivity<HistoryprojectPresenter>(),HistoryprojectContract.View {
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): HistoryprojectPresenter =HistoryprojectPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_history_project

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
