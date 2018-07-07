package com.weibiaogan.litong.adapter.work

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.weibiaogan.litong.entity.WorkListBean
import com.weibiaogan.litong.extensions.loadImag
import de.hdodenhof.circleimageview.CircleImageView

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class WorkEvaluateAdatper(workList : List<WorkEvaluateBean?>) : BaseQuickAdapter<WorkEvaluateBean,BaseViewHolder>(R.layout.activity_work_evaluate,workList){

    override fun convert(helper: BaseViewHolder?, item: WorkEvaluateBean?) {
        //helper?.setText(R.id.tv_work_item_distance,item?.distance.toString() + "m")
        //helper?.setText(R.id.tv_work_item_location,item?.distance.toString()) // location
        //helper?.setText(R.id.tv_work_item_name_phone,item?.user_phone.toString())  //name
        //helper?.setText(R.id.tv_work_item_type,item?.distance.toString())  //type

        //helper?.getView<CircleImageView>(R.id.cv_work_list_item_img)?.loadImag(item?.user_img!!,plach = R.mipmap.img_face)

    }


}