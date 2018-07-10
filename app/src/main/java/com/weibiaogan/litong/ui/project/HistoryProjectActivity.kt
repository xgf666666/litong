package com.weibiaogan.litong.ui.project

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.project.HistoryProjectAdapter
import com.weibiaogan.litong.entity.ProjectBean
import com.weibiaogan.litong.mvp.contract.HistoryprojectContract
import com.weibiaogan.litong.mvp.presenter.HistoryprojectPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_history_project.*

class HistoryProjectActivity : BaseMvpActivity<HistoryprojectPresenter>(),HistoryprojectContract.View, View.OnClickListener, OnRefreshLoadMoreListener {
    var mCurrentPage = 1
    val mStat = "2"
    var mType = 1

    var adapter = HistoryProjectAdapter(arrayListOf())
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): HistoryprojectPresenter =HistoryprojectPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_history_project

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_store_list_all.isSelected = true
        tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
        tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))

        recyclerView.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = adapter

        getPresenter().historyProject(mStat,"11","11",mCurrentPage.toString(),mType.toString())
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
        smartrefreshlayout.setOnRefreshLoadMoreListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_store_list_all -> setTextBtn(true)
            R.id.tv_store_list_distance -> setTextBtn(false)
        }
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
        getPresenter().historyProject(mStat,"11","11",mCurrentPage.toString(),mType.toString())
    }

    override fun getProjectBean(data: List<ProjectBean>) {
        if (smartrefreshlayout.isRefreshing){
            adapter.setNewData(data)
            smartrefreshlayout.finishRefresh()
            smartrefreshlayout.isEnableLoadMore = true
        }else if (smartrefreshlayout.isLoading){
            adapter.addData(data)
            smartrefreshlayout.finishLoadMore()
            if (data.isEmpty()){
                smartrefreshlayout.isEnableLoadMore = false
            }
        }else{
            if (data.isNotEmpty()){
                adapter.setNewData(data)
                smartrefreshlayout.isEnableLoadMore = true
                smartrefreshlayout.isEnableRefresh = true
            }else{
                smartrefreshlayout.isEnableLoadMore = false
                smartrefreshlayout.isEnableRefresh = false
            }
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().historyProject(mStat,"11","11",mCurrentPage.toString(),mType.toString())
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().historyProject(mStat,"11","11",(++mCurrentPage).toString(),mType.toString())
    }

}
