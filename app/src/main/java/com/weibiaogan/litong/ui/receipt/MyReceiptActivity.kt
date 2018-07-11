package com.weibiaogan.litong.ui.receipt

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.receipt.MyReceiptAdapter
import com.weibiaogan.litong.entity.MyReceiptBean
import com.weibiaogan.litong.mvp.contract.MyReceiptConstract
import com.weibiaogan.litong.mvp.presenter.MyReceiptPresenter
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import com.weibiaogan.litong.utils.addData
import com.xx.baseuilibrary.BaseActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_store_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:  我的接单
 */
class MyReceiptActivity : BaseMvpActivity<MyReceiptConstract.Presenter>(), BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener, OnRefreshLoadMoreListener , MyReceiptConstract.View{

    var adapter : MyReceiptAdapter = MyReceiptAdapter(arrayListOf())
    var stat = 1 // 1 可接单 2 已完成
    var mCurrentPage = 1 // 当前页码

    override fun createPresenter(): MyReceiptConstract.Presenter {
        return MyReceiptPresenter()
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_store_list

    override fun initData() {
        tv_store_list_title.text = "我的接单"

        tv_store_list_all.isSelected = true
        tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
        tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))
        tv_store_list_all.text = resources.getText(R.string.my_receipt_loading)
        tv_store_list_distance.text = resources.getText(R.string.my_receipt_finish)

        rv_store_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_store_list_rv.adapter = adapter

        getPresenter().workProjectList(stat.toString(),mCurrentPage.toString())
    }

    override fun initEvent() {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
        refresh_store_list.setOnRefreshLoadMoreListener(this)

        adapter.setOnItemChildClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_store_list_all -> setTextBtn(true)
            R.id.tv_store_list_distance -> setTextBtn(false)
        }
    }

    private fun setTextBtn(isAll: Boolean) {
        if ((isAll && stat == 1) || (!isAll && stat == 2)){
            return
        }
        tv_store_list_all.isSelected = isAll
        tv_store_list_distance.isSelected = !isAll
        if(isAll){
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))
            stat = 1
        }else{
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_normal))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_color_white))
            stat = 2
        }
        mCurrentPage = 1
        getPresenter().workProjectList(stat.toString(),mCurrentPage.toString())
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when(view?.id){
            R.id.tv_my_receipt_look -> startActivity(Intent(mContext,OrdersDetailActivity::class.java))
            R.id.tv_my_receipt_evaluate -> EvaluateActivity.startEvaluate(mContext,1,(adapter as MyReceiptAdapter).data[position].pt_id.toString())
        }
    }

    override fun getProjectList(data: List<MyReceiptBean>) {
        refresh_store_list.addData(adapter,data)
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().workProjectList(stat.toString(),mCurrentPage.toString())
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().workProjectList(stat.toString(),(++mCurrentPage).toString())
    }
}