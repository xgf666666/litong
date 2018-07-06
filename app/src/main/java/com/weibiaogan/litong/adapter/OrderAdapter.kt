package com.weibiaogan.litong.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.weibiaogan.litong.R

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderAdapter : RecyclerView.Adapter<OrderAdapter.ProjectHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        return ProjectHolder(LayoutInflater.from(parent.context).inflate(R.layout.rl_order,parent,false))

    }


    override fun getItemCount(): Int =10



    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        holder.rl_father?.setOnClickListener{}

    }

    class ProjectHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var rl_father:RelativeLayout?=null
        var iv_head: ImageView? = null
        var tv_title: TextView? = null
        var tv_time: TextView? = null
        var tv_distance: TextView? = null
        var tv_price: TextView? = null

        init {
            rl_father=itemView?.findViewById(R.id.rl_father)
            iv_head = itemView?.findViewById(R.id.iv_head)
            tv_title = itemView?.findViewById(R.id.tv_title)
            tv_time = itemView?.findViewById(R.id.tv_time)
            tv_distance = itemView?.findViewById(R.id.tv_distance)
            tv_price = itemView?.findViewById(R.id.tv_price)
        }
    }

}