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
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import com.weibiaogan.litong.utils.addData
import com.weibiaogan.litong.utils.initSmartRefresh
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_history_project.*

class HistoryProjectActivity : BaseMvpActivity<HistoryprojectPresenter>(),HistoryprojectContract.View, View.OnClickListener, OnRefreshLoadMoreListener {
    var mCurrentPage = 1  //当前页数
    val mStat = "2"      // 1 可接单 2 已完成
    var mType = 1          //1 全部 2 按时间

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
        adapter.isSuccess = true

        smartrefreshlayout.initSmartRefresh()

        getPresenter().historyProject(mStat,mCurrentPage.toString(),mType.toString())
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
        smartrefreshlayout.setOnRefreshLoadMoreListener(this)

        adapter.setOnItemClickListener { adapter, view, position -> OrdersDetailActivity.startProjectDetail(this,(adapter as HistoryProjectAdapter).data[position].pt_id.toString()) }
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
        getPresenter().historyProject(mStat,mCurrentPage.toString(),mType.toString())
        adapter.data.clear()
    }

    override fun getProjectBean(data: List<ProjectBean>) {
        smartrefreshlayout.addData(adapter,data)
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().historyProject(mStat,mCurrentPage.toString(),mType.toString())
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().historyProject(mStat,(++mCurrentPage).toString(),mType.toString())
    }

}
