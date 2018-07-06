package com.weibiaogan.litong.ui.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.extensions.loadImag
import de.hdodenhof.circleimageview.CircleImageView

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class ItemOneAdapter(datas : List<HomeBean.WorkerBean>?) : BaseQuickAdapter<HomeBean.WorkerBean,BaseViewHolder>(R.layout.home_item_one_view,datas) {

    override fun convert(helper: BaseViewHolder?, item: HomeBean.WorkerBean?) {
        helper?.setText(R.id.home_item_one_view_name_txt,item?.user_id.toString())
        helper?.setText(R.id.home_item_one_view_phone_txt,item?.user_phone.toString())
        helper?.setText(R.id.home_item_one_view_type_txt,"服务类型")
        helper?.setText(R.id.home_item_one_view_distance_txt,item?.distance.toString())
        helper?.getView<CircleImageView>(R.id.home_item_one_view_face_cimg)?.loadImag(item?.user_img!!,plach = R.mipmap.img_face)
        //Glide.with(mContext).load(item?.user_img).error(R.mipmap.img_face).placeholder(R.mipmap.img_face).into(helper?.getView(R.id.home_item_one_view_face_cimg))
    }
}