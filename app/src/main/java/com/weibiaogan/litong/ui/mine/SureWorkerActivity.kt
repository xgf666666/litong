package com.weibiaogan.litong.ui.mine

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.WorkDetailBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.mvp.contract.SureWorkContract
import com.weibiaogan.litong.mvp.presenter.SureWorkerPresenter
import com.weibiaogan.litong.ui.work.WorkEvaluateActivity
import com.weibiaogan.litong.utils.changeKm
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_sure_worker.*
import kotlinx.android.synthetic.main.activity_work_detail.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:确定工人
 */

class SureWorkerActivity : BaseMvpActivity<SureWorkerPresenter>(),SureWorkContract.View {

    var evaluateBean : WorkDetailBean? = null

    var pt_id = ""

    companion object {
        fun startSureWork(context: Context,work_user_id : String,pt_id : String){
            var intent = Intent(context,SureWorkerActivity::class.java)
            intent.putExtra("user_id",work_user_id)  //工人id
            intent.putExtra("pt_id",pt_id)    //项目id
            context.startActivity(intent)
        }
    }
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): SureWorkerPresenter = SureWorkerPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_sure_worker

    /**
     * 初始化数据状态
     */
    override fun initData() {
        var id = intent.getStringExtra("user_id")
        pt_id = intent.getStringExtra("pt_id")
        if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pt_id)){
            getPresenter().workDetail(id)
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_sure.setOnClickListener { getPresenter().bindWork(pt_id) }   //确定工人
        tv_cancel.setOnClickListener { getPresenter().dieWork(pt_id) }  //拒绝工人

        fl_work_detail_goto.setOnClickListener { WorkEvaluateActivity.startWorkEvaluate(this@SureWorkerActivity,evaluateBean?.user_id!!)}
    }

    /**
     * 确定工人 or 拒绝工人
     */
    override fun sureWork(msg: String) {
        showToast(msg)
    }

    /**
     * 工人详情
     */
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

