package com.weibiaogan.litong.ui.work

import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.WorkDetailBean
import com.weibiaogan.litong.extensions.loadImag
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_work_detail.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  工人详情 页面
 */
class WorkDetailActivity : BaseActivity(){
    override fun getActivityLayoutId(): Int = R.layout.activity_work_detail

    override fun initData() {
        var bean = WorkDetailBean()
        bean.address = "sdjaifdgn"
        bean.nickname = "asdgjl;"
        bean.distance = 700
        bean.user_phone = "13111111111"
        bean.user_img = ""
        setData(bean)
    }

    override fun initEvent() {

    }

    fun setData(bean: WorkDetailBean){
        tv_work_detail_distance.setText(bean.distance.toString())
        tv_work_detail_name.setText(bean.nickname)
        tv_work_detail_phone.setText(bean.user_phone)
        tv_work_detail_type.setText("")
        tv_work_detail_location.setText(bean.address)

        cv_work_detail_img.loadImag(bean.user_img!!,plach = R.mipmap.img_face)
    }
}