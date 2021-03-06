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
import com.weibiaogan.litong.ui.store.StoreDetailActivity
import com.weibiaogan.litong.ui.work.WorkDetailActivity
import com.weibiaogan.litong.utils.addData
import com.weibiaogan.litong.utils.initSmartRefresh
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_search_project.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  项目搜索 页面
 */
class SearchProjectActivity : BaseMvpActivity<SearchProjectPresenter>(), View.OnClickListener , SearchProjectContract.View, OnRefreshLoadMoreListener {
    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().searchProject(mSearchContent,(++mCurrentPage).toString())
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().searchProject(mSearchContent,mCurrentPage.toString())
    }

    var adapter : SearchProjectAdapter = SearchProjectAdapter(arrayListOf())

    var mCurrentPage = 1

    var mSearchContent = ""

    override fun createPresenter(): SearchProjectPresenter = SearchProjectPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_search_project

    override fun initData() {
        rv_search_project_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_search_project_rv.adapter = adapter

        refresh_search_project.initSmartRefresh()
    }

    override fun initEvent() {
        adapter?.setOnItemClickListener{ adapter, view, position ->
                when((adapter as SearchProjectAdapter).data[position].type){
                    1->OrdersDetailActivity.startProjectDetail(mContext,adapter .data[position].pt_id.toString())
                    2->WorkDetailActivity.startWorkDetail(mContext,adapter .data[position].user_id)
                    3->StoreDetailActivity.startStoreDetail(mContext,adapter.data[position].st_id)
                }
        }
        refresh_search_project.setOnRefreshLoadMoreListener(this)

        tv_search_project_search.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        mSearchContent = et_search_project_edit.text.toString().trim()
        if (!TextUtils.isEmpty(mSearchContent)){
            getPresenter().searchProject(mSearchContent,mCurrentPage.toString())
        }else{
            showToast("请输入搜索内容")
        }
    }

    override fun getSearchResult(bean: List<SearchProjectBean>) {
        refresh_search_project.addData(adapter,bean)
        /*if (refresh_search_project.isLoading){
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
        }*/
    }
}