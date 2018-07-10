package com.weibiaogan.litong.ui.store

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.store.StoreListAdapter
import com.weibiaogan.litong.entity.StoreListBean
import com.weibiaogan.litong.mvp.contract.StoreListConstract
import com.weibiaogan.litong.mvp.presenter.StoreListPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_store_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  店铺列表 页面
 */
class StoreListActivity : BaseMvpActivity<StoreListConstract.Presenter>(), View.OnClickListener , StoreListConstract.View, OnRefreshLoadMoreListener {
    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().storeList((++mCurrentPage).toString(),"11","11",mType.toString())
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().storeList(mCurrentPage.toString(),"11","11",mType.toString())
    }

    var adapter : StoreListAdapter = StoreListAdapter(arrayListOf())

    var mCurrentPage = 1  //当前请求的页码

    var mType = 1   // 1： 全部 2：距离

    override fun createPresenter(): StoreListConstract.Presenter {
        return StoreListPresenter()
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_store_list

    override fun initData() {
        tv_store_list_all.text = resources.getText(R.string.store_list_all)
        tv_store_list_distance.text = resources.getText(R.string.store_list_distance)
        tv_store_list_all.isSelected = true
        tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
        tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))

        rv_store_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_store_list_rv.adapter = adapter

        getPresenter().storeList(mCurrentPage.toString(),"11","11",mType.toString())
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
        getPresenter().storeList(mCurrentPage.toString(),"11","11",mType.toString())
    }

    override fun initEvent() {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
        refresh_store_list.setOnRefreshLoadMoreListener(this)

        adapter?.setOnItemClickListener { adapter, view, position ->
            StoreDetailActivity.startStoreDetail(this@StoreListActivity,(adapter as StoreListAdapter).data.get(position).st_id) }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_store_list_all -> setTextBtn(true)
            R.id.tv_store_list_distance -> setTextBtn(false)
        }
    }

    override fun getStoreListData(data: List<StoreListBean>) {
        if (refresh_store_list.isRefreshing){
            adapter.setNewData(data)
            refresh_store_list.finishRefresh()
            refresh_store_list.isEnableLoadMore = true
        }else if (refresh_store_list.isLoading){
            adapter.addData(data)
            refresh_store_list.finishLoadMore()
            if (data.isEmpty()){
                refresh_store_list.isEnableLoadMore = false
            }
        }else{
            if (data.isNotEmpty()){
                adapter.setNewData(data)
                refresh_store_list.isEnableRefresh = true
                refresh_store_list.isEnableLoadMore = true
            }else{
                refresh_store_list.isEnableRefresh = false
                refresh_store_list.isEnableLoadMore = false
            }
        }
    }
}