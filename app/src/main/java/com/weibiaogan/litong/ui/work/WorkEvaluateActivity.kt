package com.weibiaogan.litong.ui.work

import android.content.Context
import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.work.WorkEvaluateAdatper
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.weibiaogan.litong.mvp.contract.WorkEvaluateConstract
import com.weibiaogan.litong.mvp.presenter.WorkEvaluatePresenter
import com.weibiaogan.litong.utils.addData
import com.weibiaogan.litong.utils.initSmartRefresh
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_work_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人评价  需求方评价 页面
 */
class WorkEvaluateActivity : BaseMvpActivity<WorkEvaluateConstract.Presenter>(),WorkEvaluateConstract.View, OnRefreshLoadMoreListener {

    var adapter = WorkEvaluateAdatper(arrayListOf())

    var mUserId = -1
    var mCurrentPage = 1
    var mType = 1

    companion object {
        fun startWorkEvaluate(context: Context,type : Int ,  user_id : Int){
            var intent = Intent(context,WorkEvaluateActivity::class.java)
            intent.putExtra("work_evaluate_id",user_id)
            intent.putExtra("evaluate_type",type)
            context.startActivity(intent)
        }
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_work_list

    override fun initData() {

        mUserId = intent.getIntExtra("work_evaluate_id", -1)
        mType = intent.getIntExtra("evaluate_type",-1)
        if (mType == 1){
            tv_work_title.text = "需求方评价"
            getPresenter().projectEvaluate(mUserId.toString(),mCurrentPage.toString())
        }else if (mType == 0){
            tv_work_title.text = resources.getString(R.string.work_evaluate_title)
            getPresenter().workEvaluate(mUserId.toString(),mCurrentPage.toString())
        }

        rv_work_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_work_list_rv.adapter = adapter

        refresh_work_list.initSmartRefresh()
    }

    override fun initEvent() {
        refresh_work_list.setOnRefreshLoadMoreListener(this)
    }

    override fun createPresenter(): WorkEvaluateConstract.Presenter {
        return WorkEvaluatePresenter()
    }

    override fun getWorkEvaluateData(data: WorkEvaluateBean) {
        refresh_work_list.addData(adapter,data.data)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        if (mType == 1){
            getPresenter().projectEvaluate(mUserId.toString(),(++mCurrentPage).toString())
        }else if (mType == 0){
            getPresenter().workEvaluate(mUserId.toString(),(++mCurrentPage).toString())
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        if (mType == 1){
            getPresenter().projectEvaluate(mUserId.toString(),mCurrentPage.toString())
        }else if (mType == 0){
            getPresenter().workEvaluate(mUserId.toString(),mCurrentPage.toString())
        }
    }
}