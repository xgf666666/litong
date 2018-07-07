package com.weibiaogan.litong.adapter.mine

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.weibiaogan.litong.R
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:
 */
class OrderAdapter(context: Context) : RecyclerView.Adapter<OrderAdapter.ProjectHolder>() {
    var context: Context? = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        return ProjectHolder(View.inflate(parent.context, R.layout.rl_order, null))
    }
    override fun getItemCount(): Int =10



    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        holder.rl_father?.setOnClickListener{context!!.startActivity(Intent(context,OrdersDetailActivity::class.java))}

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