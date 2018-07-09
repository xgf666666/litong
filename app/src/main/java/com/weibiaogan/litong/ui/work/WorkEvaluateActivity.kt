package com.weibiaogan.litong.ui.work

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
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
class WorkEvaluateActivity : BaseMvpActivity<WorkEvaluateConstract.Presenter>(),WorkEvaluateConstract.View{

    var adapter = WorkEvaluateAdatper(arrayListOf())

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

        var user_id = intent.getIntExtra("work_evaluate_id", -1)
        if (user_id != -1){
            getPresenter().workEvaluate(user_id.toString())
        }else{
            finish()
        }

        rv_work_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_work_list_rv.adapter = adapter

    }

    override fun initEvent() {

    }

    override fun createPresenter(): WorkEvaluateConstract.Presenter {
        return WorkEvaluatePresenter()
    }

    override fun getWorkEvaluateData(data: List<WorkEvaluateBean>) {
        adapter.setNewData(data)
    }
}