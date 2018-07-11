package com.weibiaogan.litong.ui.mine

import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.SureWorkContract
import com.weibiaogan.litong.mvp.presenter.SureWorkerPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:确定工人
 */

class SureWorkerActivity : BaseMvpActivity<SureWorkerPresenter>(),SureWorkContract.View {
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): SureWorkerPresenter = SureWorkerPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_sure_worker

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
