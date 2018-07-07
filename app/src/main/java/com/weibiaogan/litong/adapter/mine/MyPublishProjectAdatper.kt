package com.weibiaogan.litong.adapter.mine

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.weibiaogan.litong.R

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:
 */
class MyPublishProjectAdatper:RecyclerView.Adapter<MyPublishProjectAdatper.ProjectHolder>() {

    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        return ProjectHolder(View.inflate(parent.context, R.layout.item_mypublishproject, null))
    }


    override fun getItemCount(): Int =10




    class ProjectHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var rl_father: RelativeLayout?=null
        var iv_head: ImageView? = null
        var tv_title: TextView? = null
        var tv_state: TextView? = null
        var tv_one: TextView? = null
        var tv_two: TextView? = null
        var tv_three: TextView? = null
        var tv_price: TextView? = null

        init {
            rl_father=itemView?.findViewById(R.id.rl_father)
            iv_head = itemView?.findViewById(R.id.iv_head)
            tv_title = itemView?.findViewById(R.id.tv_title)
            tv_state = itemView?.findViewById(R.id.tv_state)
            tv_one = itemView?.findViewById(R.id.tv_one)
            tv_two = itemView?.findViewById(R.id.tv_two)
            tv_three = itemView?.findViewById(R.id.tv_three)
            tv_price = itemView?.findViewById(R.id.tv_price)
        }
    }
}