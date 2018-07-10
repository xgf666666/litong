package com.weibiaogan.litong.adapter.project

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.ProjectBean
import com.weibiaogan.litong.extensions.loadImag

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class HistoryProjectAdapter(data:List<ProjectBean>) :BaseQuickAdapter<ProjectBean,BaseViewHolder>(R.layout.item_historyproject,data){
    /**
     *
     */
    override fun convert(helper: BaseViewHolder?, item: ProjectBean?) {
        helper?.setText(R.id.tv_title,item?.pt_name)
                ?.setText(R.id.tv_distance,item?.distance.toString())
                ?.setText(R.id.tv_time,item?.end_time)
                ?.setText(R.id.tv_price,item?.all_price)
        helper?.getView<ImageView>(R.id.iv_success)?.visibility = View.VISIBLE
        helper?.getView<ImageView>(R.id.iv_head)?.loadImag(item?.pt_imgs!!)
    }
}