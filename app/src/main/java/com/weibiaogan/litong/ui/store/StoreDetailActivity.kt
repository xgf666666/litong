package com.weibiaogan.litong.ui.store

import android.content.Context
import android.content.Intent
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.StoreDetailBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.mvp.contract.StoreDetailConstract
import com.weibiaogan.litong.mvp.presenter.StoreDetailPresenter
import com.weibiaogan.litong.ui.location.GeoToScreenActivity
import com.weibiaogan.litong.utils.changeKm
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_store_detail.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  店铺详情 页面
 */
class StoreDetailActivity : BaseMvpActivity<StoreDetailConstract.Presenter>(),StoreDetailConstract.View{

    companion object {
        fun startStoreDetail(context: Context, user_id : Int){
            var intent = Intent(context, StoreDetailActivity::class.java)
            intent.putExtra("store_detail_id",user_id)
            context.startActivity(intent)
        }
    }

    override fun createPresenter(): StoreDetailConstract.Presenter = StoreDetailPresenter()



    override fun getActivityLayoutId(): Int = R.layout.activity_store_detail

    override fun initData() {
        var id = intent.getIntExtra("store_detail_id", -1)
        if (id != -1){
            getPresenter().storeDetail(id.toString(),"11","11")
        }
    }

    override fun initEvent() {
        tv_store_detail_goto_distance.setOnClickListener { startActivity(Intent(mContext,GeoToScreenActivity::class.java)) }
    }

    override fun getStoreDetailData(bean: StoreDetailBean) {
        tv_store_detail_name.text = (bean.st_name)
        tv_store_detail_phone.text = ("电话 : "+bean.st_phone)//phone
        tv_store_detail_address.text = ("地址 : " + bean.st_address.trim())
        tv_store_detail_goto_distance.text = ("距离"+bean.distance.changeKm())
        tv_store_detail_time.text = ("营业时间 : "+bean.business_hours.trim())
        tv_store_detail_type.text = ("主营类别 : "+bean.st_type.trim())

        iv_store_detail_img.loadImag(bean.st_img,plach = R.mipmap.ic_launcher)
    }


}