package com.weibiaogan.litong.ui.orders

import android.app.AlertDialog
import android.view.View
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.OrdersDetailCOntract
import com.weibiaogan.litong.mvp.model.OrderdDetailModel
import com.weibiaogan.litong.mvp.presenter.OrderDetailPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_sure_worker.*

/**
 * author: xiaoguangfei
 * date: 2018/7/5
 * describe:项目详情
 */
class OrdersDetailActivity : BaseMvpActivity<OrderDetailPresenter>(),OrdersDetailCOntract.View {
    /**
     * 填充数据
     *
     * @param data 数据
     */
    override fun setData(data: List<Any>?) {
    }

    /**
     * 显示错误信息
     *
     * @param throwable 错误
     * @param refresh   是否是刷新
     */
    override fun showError(throwable: Throwable, refresh: Boolean) {
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): OrderDetailPresenter =OrderDetailPresenter()
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
//        showDialog()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        ib_back.setOnClickListener{finish()}

    }
    fun showDialog(){

        var view= View.inflate(mContext,R.layout.dialog_orderdetail,null)

        var dialog=AlertDialog.Builder(mContext)
        dialog.setView(view).show()
    }


}
