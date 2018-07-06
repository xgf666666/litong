package com.weibiaogan.litong.ui.orders

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.RelativeLayout
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.OrderAdapter
import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.weibiaogan.litong.mvp.presenter.OrdersPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import kotlinx.android.synthetic.main.fragment_orders.*


/**
 * author: xiaoguangfei
 * date: 2018/7/5
 * describe:我要接单
 */
class OrdersFragment : BaseMvpLcecFragment<RelativeLayout, List<Any>, OrdersContract.Model, OrdersContract.View, OrdersPresenter>(), OrdersContract.View, SwipeRefreshLayout.OnRefreshListener {
    /**
     * Called when a swipe gesture triggers a refresh.
     */

    private var orderAdapter:OrderAdapter?=null


    override fun getFragmentLayoutId(): Int = R.layout.fragment_orders


    override fun loadData(refresh: Boolean) {

    }

    override fun createPresenter(): OrdersPresenter {
        return OrdersPresenter()
    }

    override fun initEvent(view: View?) {
    }

    override fun initData() {
        recyclerView.layoutManager=LinearLayoutManager(context)
        orderAdapter= OrderAdapter(context!!)
        recyclerView.adapter=orderAdapter
        swiperefreshlayout.setOnRefreshListener(this)

    }
    override fun onRefresh() {

    }


    override fun setData(data: List<Any>) {
    }





}