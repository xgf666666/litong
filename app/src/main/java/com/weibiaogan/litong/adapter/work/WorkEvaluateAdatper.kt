package com.weibiaogan.litong.adapter.work

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.WorkEvaluateBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.widget.StarView
import de.hdodenhof.circleimageview.CircleImageView

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class WorkEvaluateAdatper(workList : List<WorkEvaluateBean.DataBean?>) : BaseQuickAdapter<WorkEvaluateBean.DataBean,BaseViewHolder>(R.layout.activity_work_evaluate,workList){

    override fun convert(helper: BaseViewHolder?, item: WorkEvaluateBean.DataBean?) {

        helper?.setText(R.id.tv_work_evaluate_name,item?.user_data?.nickname)
                ?.setText(R.id.tv_work_evaluate_contents,item?.com_content?.trim())
                ?.setText(R.id.tv_work_evaluate_time,item?.add_time?.trim())
        helper?.getView<StarView>(R.id.sv_work_evaluate_star)?.setStarNum(item?.score!!)
        helper?.getView<CircleImageView>(R.id.cv_work_evaluate_img)?.loadImag(item?.user_data?.user_img!!,plach = R.mipmap.img_face)

        var layout = helper?.getView<LinearLayout>(R.id.ll_work_evaluate_imgs)
        var mImgList = arrayListOf<ImageView>()
        for (i in 0 until layout?.childCount!!){
            var imageView = layout.getChildAt(i) as ImageView
            mImgList.add(imageView)
        }
        for (i in 0 until item?.com_imgs!!.size){
            if (!TextUtils.isEmpty(item?.com_imgs[i])){
                mImgList[i].visibility = View.VISIBLE
                mImgList[i].loadImag(item?.com_imgs[i],plach = R.mipmap.img_default)
            }else{
                //mImgList[i].visibility = View.GONE
            }
        }

    }


}