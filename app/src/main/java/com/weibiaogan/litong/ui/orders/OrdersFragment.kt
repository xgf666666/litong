package com.weibiaogan.litong.ui.orders

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.project.HistoryProjectAdapter
import com.weibiaogan.litong.entity.ProjectBean
import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.weibiaogan.litong.mvp.presenter.OrdersPresenter
import com.weibiaogan.litong.utils.addData
import com.weibiaogan.litong.utils.initSmartRefresh
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import kotlinx.android.synthetic.main.fragment_orders.*


/**
 * author: xiaoguangfei
 * date: 2018/7/5
 * describe:我要接单
 */
class OrdersFragment : BaseMvpLcecFragment<RelativeLayout, List<ProjectBean>, OrdersContract.Model, OrdersContract.View, OrdersPresenter>(), OrdersContract.View, View.OnClickListener, OnRefreshLoadMoreListener {

    var mCurrentPage = 1  //当前页数
    val mStat = "1"      // 1 可接单 2 已完成
    var mType = 1          //1 全部 2 按时间

    var adapter = HistoryProjectAdapter(arrayListOf())


    override fun getFragmentLayoutId(): Int = R.layout.fragment_orders


    override fun loadData(refresh: Boolean) {

    }

    override fun createPresenter(): OrdersPresenter {
        return OrdersPresenter()
    }

    override fun initEvent(view: View?) {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
        swiperefreshlayout.setOnRefreshLoadMoreListener(this)

        adapter.setOnItemClickListener { adapter, view, position -> OrdersDetailActivity.startProjectDetail(mContext,(adapter as HistoryProjectAdapter).data[position].pt_id.toString()) }
    }

    private fun setTextBtn(isAll: Boolean) {
        if ((isAll && mType == 1) || (!isAll && mType == 2)){
            return
        }
        mCurrentPage = 1
        tv_store_list_all.isSelected = isAll
        tv_store_list_distance.isSelected = !isAll
        if(isAll){
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))
            mType = 1
        }else{
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_normal))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_color_white))
            mType = 2
        }
        presenter.historyProject(mStat,mCurrentPage.toString(),mType.toString())
        adapter.data.clear()
    }

    override fun initData() {
        tv_store_list_all.isSelected = true
        tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
        tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))   //初始化全部按钮

        recyclerView.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter
        adapter.isSuccess = false

        swiperefreshlayout.initSmartRefresh()

        presenter.historyProject(mStat,mCurrentPage.toString(),mType.toString())

    }

    override fun setData(data: List<ProjectBean>) {
        showContent()
        swiperefreshlayout.addData(adapter,data)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_store_list_all -> setTextBtn(true)
            R.id.tv_store_list_distance -> setTextBtn(false)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        presenter.historyProject(mStat,mCurrentPage.toString(),mType.toString())
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        presenter.historyProject(mStat,(++mCurrentPage).toString(),mType.toString())
    }
}