package com.weibiaogan.litong.adapter.mine

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.Order
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import java.net.URL

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderAdapter(data: ArrayList<Order>?) :BaseQuickAdapter<Order,BaseViewHolder>(R.layout.rl_order) {
    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder?, item: Order?) {

//        val sourceStrArray = item.pt_imgs.split(",")
        Glide.with(mContext).load(URL(item?.pt_imgs)).into(helper?.getView<ImageView>(R.id.iv_head))
        helper?.setText(R.id.tv_title,item?.pt_name)
        helper?.setText(R.id.tv_time,item?.add_time)
        helper?.setText(R.id.tv_distance,"截止时间："+item?.add_time)

    }


//    class ProjectHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
//        var rl_father:RelativeLayout?=null
//        var iv_head: ImageView? = null
//        var tv_title: TextView? = null
//        var tv_time: TextView? = null
//        var tv_distance: TextView? = null
//        var tv_price: TextView? = null
//
//        init {
//            rl_father=itemView?.findViewById(R.id.rl_father)
//            iv_head = itemView?.findViewById(R.id.iv_head)
//            tv_title = itemView?.findViewById(R.id.tv_title)
//            tv_time = itemView?.findViewById(R.id.tv_time)
//            tv_distance = itemView?.findViewById(R.id.tv_distance)
//            tv_price = itemView?.findViewById(R.id.tv_price)
//        }
//    }

}