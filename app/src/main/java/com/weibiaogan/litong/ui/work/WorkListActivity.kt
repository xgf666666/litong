package com.weibiaogan.litong.ui.work

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.work.WorkListAdatper
import com.weibiaogan.litong.entity.WorkListBean
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_work_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人列表 页面
 */
class WorkListActivity : BaseActivity(){

    var adapter : WorkListAdatper? = null

    override fun getActivityLayoutId(): Int = R.layout.activity_work_list

    override fun initData() {
        tv_work_title.setText(resources.getString(R.string.work_list_title))
        rv_work_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        var list = arrayListOf<WorkListBean>()
        for (i in 0..10){
            var bean = WorkListBean()
            bean.distance = 700
            bean.user_img = ""
            bean.user_phone = "130000000000"
            list.add(bean)
        }
        adapter = WorkListAdatper(list)
        rv_work_list_rv.adapter = adapter

        adapter?.setOnItemClickListener { adapter, view, position -> startActivity(Intent(mContext,WorkDetailActivity::class.java)) }
    }

    override fun initEvent() {

    }
}