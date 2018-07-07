package com.weibiaogan.litong.ui.work

import android.support.v7.widget.LinearLayoutManager
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.work.WorkEvaluateAdatper
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_work_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人评价 页面
 */
class WorkEvaluateActivity : BaseActivity(){
    override fun getActivityLayoutId(): Int = R.layout.activity_work_list

    override fun initData() {
        tv_work_title.setText(resources.getString(R.string.work_evaluate_title))

        var wList = arrayListOf<WorkEvaluateBean>()

        for (i in 0..10){
            var workEvaluateBean = WorkEvaluateBean()



            wList.add(workEvaluateBean)
        }

        rv_work_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_work_list_rv.adapter = WorkEvaluateAdatper(wList)

    }

    override fun initEvent() {

    }
}