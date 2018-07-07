package com.weibiaogan.litong.ui.mine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.extensions.startActivity
import com.weibiaogan.litong.mvp.contract.WorkerIdentyContract
import com.weibiaogan.litong.mvp.presenter.WorkeridentyPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_bos_identy.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:工人认证1
 */


class WorkerIDentyOneActivity : BaseMvpActivity<WorkeridentyPresenter>(),WorkerIdentyContract.View{
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): WorkeridentyPresenter =WorkeridentyPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_worker_identy_one

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
