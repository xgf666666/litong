package com.weibiaogan.litong.ui.mine

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.WorkerIdentyTwoContrat
import com.weibiaogan.litong.mvp.presenter.WorkerIdentyTwoPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity


/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:工人认证2
 */
class WorkerIDentyTwoActivity : BaseMvpActivity<WorkerIdentyTwoPresenter>(),WorkerIdentyTwoContrat.View {
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): WorkerIdentyTwoPresenter =WorkerIdentyTwoPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_worker_identy_two

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
