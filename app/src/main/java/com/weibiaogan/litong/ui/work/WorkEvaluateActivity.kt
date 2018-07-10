package com.weibiaogan.litong.ui.work

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.work.WorkEvaluateAdatper
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.weibiaogan.litong.mvp.contract.WorkEvaluateConstract
import com.weibiaogan.litong.mvp.presenter.WorkEvaluatePresenter
import com.xx.baseuilibrary.BaseActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_work_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人评价 页面
 */
class WorkEvaluateActivity : BaseMvpActivity<WorkEvaluateConstract.Presenter>(),WorkEvaluateConstract.View, OnRefreshLoadMoreListener {

    var adapter = WorkEvaluateAdatper(arrayListOf())

    var mUserId = -1
    var mCurrentPage = 1

    companion object {
        fun startWorkEvaluate(context: Context, user_id : Int){
            var intent = Intent(context,WorkEvaluateActivity::class.java)
            intent.putExtra("work_evaluate_id",user_id)
            context.startActivity(intent)
        }
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_work_list

    override fun initData() {
        tv_work_title.text = resources.getString(R.string.work_evaluate_title)

        mUserId = intent.getIntExtra("work_evaluate_id", -1)
        if (mUserId != -1){
            getPresenter().workEvaluate(mUserId.toString(),mCurrentPage.toString())
        }else{
            finish()
        }

        rv_work_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_work_list_rv.adapter = adapter

    }

    override fun initEvent() {
        refresh_work_list.setOnRefreshLoadMoreListener(this)
    }

    override fun createPresenter(): WorkEvaluateConstract.Presenter {
        return WorkEvaluatePresenter()
    }

    override fun getWorkEvaluateData(data: WorkEvaluateBean) {
        if (refresh_work_list.isLoading){
            adapter.addData(data.data)
            refresh_work_list.finishLoadMore()
            if (data.data.isEmpty()){
                refresh_work_list.isEnableLoadMore = false
            }
        }else if (refresh_work_list.isRefreshing){
            adapter.setNewData(data.data)
            refresh_work_list.finishRefresh()
            refresh_work_list.isEnableLoadMore = true
        }else{
            if (data.data.isEmpty()){
                refresh_work_list.isEnableLoadMore = false
                refresh_work_list.isEnableRefresh = false
                return
            }
            adapter.setNewData(data.data)
            refresh_work_list.isEnableLoadMore = true
            refresh_work_list.isEnableRefresh = true
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().workEvaluate(mUserId.toString(),(++mCurrentPage).toString())
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().workEvaluate(mUserId.toString(),mCurrentPage.toString())
    }
}