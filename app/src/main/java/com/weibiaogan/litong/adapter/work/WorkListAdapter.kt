package com.weibiaogan.litong.adapter.work

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.home.changeKm
import com.weibiaogan.litong.entity.WorkListBean
import com.weibiaogan.litong.extensions.loadImag
import de.hdodenhof.circleimageview.CircleImageView

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class WorkListAdapter(workList : List<WorkListBean?>) : BaseQuickAdapter<WorkListBean,BaseViewHolder>(R.layout.work_list_item,workList){

    override fun convert(helper: BaseViewHolder?, item: WorkListBean?) {
        helper?.setText(R.id.tv_work_item_distance,item?.distance?.changeKm())
        helper?.setText(R.id.tv_work_item_location,item?.worker_address.toString())
        helper?.setText(R.id.tv_work_item_name_phone,item?.nickname + "  " + item?.user_phone.toString())
        helper?.setText(R.id.tv_work_item_type,"服务类型" + item?.worker_service.toString())

        helper?.getView<CircleImageView>(R.id.cv_work_list_item_img)?.loadImag(item?.user_img!!,plach = R.mipmap.img_face)

    }


}