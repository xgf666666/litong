package com.weibiaogan.litong.ui.store

import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.StoreDetailBean
import com.weibiaogan.litong.extensions.loadImag
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_store_detail.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  店铺详情 页面
 */
class StoreDetailActivity : BaseActivity(){
    override fun getActivityLayoutId(): Int = R.layout.activity_store_detail

    override fun initData() {
        var bean = StoreDetailBean()
        bean.business_hours = "10:30 - 20:30"
        bean.st_name = "affg"
        bean.st_type = "fag"
        bean.st_address = "fsaghh"
        bean.distance = 700
        bean.st_img = ""
        setData(bean)

    }

    override fun initEvent() {

    }

    fun setData(bean: StoreDetailBean){
        tv_store_detail_name.setText(bean.st_name)
        tv_store_detail_phone.setText("13132.00")//phone
        tv_store_detail_address.setText(bean.st_address)
        tv_store_detail_goto_distance.setText(bean.distance.toString())
        tv_store_detail_time.setText(bean.business_hours)
        tv_store_detail_type.setText(bean.st_type)

        iv_store_detail_img.loadImag(bean.st_img,plach = R.mipmap.ic_launcher)
    }
}