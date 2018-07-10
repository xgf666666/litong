package com.weibiaogan.litong.ui.search

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
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
class SearchProjectActivity : BaseMvpActivity<SearchProjectContract.Presenter>(), View.OnClickListener , SearchProjectContract.View, OnRefreshLoadMoreListener {
    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().searchProject(mSearchContent,(++mCurrentPage).toString(),"116.557684","23.192033")
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().searchProject(mSearchContent,mCurrentPage.toString(),"116.557684","23.192033")
    }

    var adapter : SearchProjectAdapter = SearchProjectAdapter(arrayListOf())

    var mCurrentPage = 1

    var mSearchContent = ""

    override fun createPresenter(): SearchProjectContract.Presenter = SearchProjectPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_search_project

    override fun initData() {
        rv_search_project_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_search_project_rv.adapter = adapter
    }

    override fun initEvent() {
        adapter?.setOnItemClickListener { adapter, view, position -> startActivity(Intent(mContext,OrdersDetailActivity::class.java)) }

        refresh_search_project.setOnRefreshLoadMoreListener(this)

        tv_search_project_search.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        mSearchContent = et_search_project_edit.text.toString().trim()
        if (!TextUtils.isEmpty(mSearchContent)){
            getPresenter().searchProject(mSearchContent,mCurrentPage.toString(),"116.557684","23.192033")
        }else{
            showToast("请输入搜索内容")
        }
    }

    override fun getSearchResult(bean: List<SearchProjectBean>) {
        if (refresh_search_project.isLoading){
            adapter.addData(bean)
            refresh_search_project.finishLoadMore()
            if (bean.isEmpty()){
                refresh_search_project.isEnableLoadMore = false
            }
        }else if (refresh_search_project.isRefreshing){
            adapter.setNewData(bean)
            refresh_search_project.finishRefresh()
            refresh_search_project.isEnableLoadMore = true
        }else{
            if (bean.isNotEmpty()){
                adapter.setNewData(bean)
                refresh_search_project.isEnableRefresh = true
                refresh_search_project.isEnableLoadMore = true
            }else{
                refresh_search_project.isEnableRefresh = false
                refresh_search_project.isEnableLoadMore = false
            }
        }
    }
}