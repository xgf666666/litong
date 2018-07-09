package com.weibiaogan.litong.ui.search

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.search.SearchProjectAdapter
import com.weibiaogan.litong.entity.SearchProjectBean
import com.weibiaogan.litong.mvp.contract.SearchProjectContract
import com.weibiaogan.litong.mvp.presenter.SearchProjectPresenter
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_search_project.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  项目搜索 页面
 */
class SearchProjectActivity : BaseMvpActivity<SearchProjectContract.Presenter>(){

    var adapter : SearchProjectAdapter = SearchProjectAdapter(arrayListOf())

    override fun createPresenter(): SearchProjectContract.Presenter = SearchProjectPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_search_project

    override fun initData() {
        rv_search_project_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_search_project_rv.adapter = adapter
    }

    override fun initEvent() {
        adapter?.setOnItemClickListener { adapter, view, position -> startActivity(Intent(mContext,OrdersDetailActivity::class.java)) }

        var search = et_search_project_edit.text.toString().trim()
    }
}