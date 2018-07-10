package com.weibiaogan.litong.adapter.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.utils.changeKm

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class ItemThreeAdapter(datas : List<HomeBean.ProjectBean>?) : BaseQuickAdapter<HomeBean.ProjectBean, BaseViewHolder>(R.layout.home_item_three_view,datas){

    override fun convert(helper: BaseViewHolder?, item: HomeBean.ProjectBean?) {
        helper?.setText(R.id.tv_home_item_three_name,item?.pt_name)
        helper?.setText(R.id.tv_home_item_three_price,"￥ "+item?.all_price)
        helper?.setText(R.id.tv_home_item_three_time,"截止时间 ："+item?.end_time)
        helper?.setText(R.id.tv_home_item_three_distance,item?.distance?.changeKm())
        helper?.getView<ImageView>(R.id.iv_home_item_three_img)?.loadImag(item?.pt_imgs!!,plach = R.mipmap.img_face)
    }

}