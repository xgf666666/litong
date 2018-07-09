package com.weibiaogan.litong.ui.work

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.work.WorkListAdapter
import com.weibiaogan.litong.entity.WorkListBean
import com.weibiaogan.litong.mvp.contract.WorkListConstract
import com.weibiaogan.litong.mvp.presenter.WorkListPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_work_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人列表 页面
 */
class WorkListActivity : BaseMvpActivity<WorkListConstract.Presenter>(),WorkListConstract.View{
    override fun createPresenter(): WorkListConstract.Presenter = WorkListPresenter()

    var adapter : WorkListAdapter = WorkListAdapter(arrayListOf())

    override fun getActivityLayoutId(): Int = R.layout.activity_work_list

    override fun initData() {
        tv_work_title.setText(resources.getString(R.string.work_list_title))
        rv_work_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_work_list_rv.adapter = adapter

        getPresenter().workerList("1","11","11")
    }

    override fun initEvent() {
        adapter.setOnItemClickListener { adapter, view, position ->
            WorkDetailActivity.startWorkDetail(this@WorkListActivity,(adapter as WorkListAdapter).data.get(position).user_id)
        }
    }

    override fun getWorkListData(data: List<WorkListBean>) {
        adapter.setNewData(data)
    }
}