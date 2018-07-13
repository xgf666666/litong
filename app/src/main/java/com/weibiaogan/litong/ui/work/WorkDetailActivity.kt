package com.weibiaogan.litong.ui.work

import android.content.Context
import android.content.Intent
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.WorkDetailBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.mvp.contract.WorkDetailConstract
import com.weibiaogan.litong.mvp.presenter.WorkDetailPresenter
import com.weibiaogan.litong.utils.changeKm
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_work_detail.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人详情 页面
 */
class WorkDetailActivity : BaseMvpActivity<WorkDetailConstract.Presenter>(),WorkDetailConstract.View{

    var evaluateBean : WorkDetailBean? = null

    companion object {
            fun startWorkDetail(context: Context,user_id : Int){
            var intent = Intent(context,WorkDetailActivity::class.java)
            intent.putExtra("work_detail_id",user_id)
            context.startActivity(intent)
        }
    }

    override fun createPresenter(): WorkDetailConstract.Presenter = WorkDetailPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_work_detail

    override fun initData() {
        var work_user_id = intent.getIntExtra("work_detail_id", -1)
        if (work_user_id != -1){
            getPresenter().workDetail(work_user_id.toString())
        }else{
            finish()
        }
    }

    override fun initEvent() {
        fl_work_detail_goto.setOnClickListener { WorkEvaluateActivity.startWorkEvaluate(this@WorkDetailActivity,0,evaluateBean?.user_id!!)}
    }

    override fun getWorkDetailData(bean: WorkDetailBean) {
        evaluateBean = bean
        tv_work_detail_distance.text = bean.distance.changeKm()
        tv_work_detail_name.text = bean.nickname
        tv_work_detail_phone.text = bean.user_phone
        tv_work_detail_type.text = "服务类型：" + bean.worker_service
        tv_work_detail_location.text = "所在地：" + bean.worker_address

        if (bean.user_sex == 1) {   //女
            iv_work_detail_sex.setImageResource(R.mipmap.ic_female)
        }else{  //2男
            iv_work_detail_sex.setImageResource(R.mipmap.ic_male)
        }

        cv_work_detail_img.loadImag(bean.user_img!!,plach = R.mipmap.img_face)
    }
}