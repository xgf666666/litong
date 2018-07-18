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
 * describe:  我要找材料
 */
class ItemTwoAdapter(datas : List<HomeBean.StoreBean>?) : BaseQuickAdapter<HomeBean.StoreBean, BaseViewHolder>(R.layout.home_item_two_view,datas){

    override fun convert(helper: BaseViewHolder?, item: HomeBean.StoreBean?) {
        helper?.setText(R.id.tv_home_item_two_name,item?.st_name)
        helper?.setText(R.id.tv_home_item_two_type,item?.st_type)
        helper?.setText(R.id.tv_home_item_two_distance,item?.distance?.changeKm())
        helper?.getView<ImageView>(R.id.iv_home_item_two_face)?.loadImag(item?.st_img!! , plach = R.mipmap.img_default)
    }

}