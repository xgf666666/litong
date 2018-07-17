package com.weibiaogan.litong.adapter.mine

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.PublicProjectBean
import com.weibiaogan.litong.extensions.loadImag

/**
 * author: HuaiXianZhong
 * date: 2018/7/11
 * describe:我的发布
 */
class MyPublishProjectAdapter(data : List<PublicProjectBean.DataBean>) : BaseQuickAdapter<PublicProjectBean.DataBean,BaseViewHolder>(R.layout.item_mypublishproject,data){

    /**
     * pt_stat  状态 1是已取消 2是已审核上架 3是用户接单中 4是用户成功接单 5是支付一期款 6是支付二期款 7是支付三期款
    当pt_stat=1,则项目出现在完成列表，需求方列表里，如果boss_comments=0，则出现评论按钮，boss_comments=1，则没有。‘查看项目按钮’一直有。

    当pt_stat=2,出现‘取消项目’按钮。查看项目按钮。
    当pt_stat=3,出现‘取消项目’按钮。查看项目按钮。‘确认工人’按钮
    当pt_stat=4,出现‘取消项目’按钮。查看项目按钮。‘付首款’按钮
    当pt_stat=5,出现‘取消项目’按钮。查看项目按钮。‘二期款’按钮
    当pt_stat=6,出现‘取消项目’按钮。查看项目按钮。‘尾款’按钮
    当pt_stat=7,则项目出现在完成列表，需求方列表里，如果boss_comments=0，则出现评论按钮，boss_comments=1，则没有。‘查看项目按钮’一直有。
     */

    override fun convert(helper: BaseViewHolder?, item: PublicProjectBean.DataBean?) {
        helper?.setText(R.id.tv_state,item?.pt_stat_detail)
                ?.setText(R.id.tv_title,item?.pt_name)
                ?.setText(R.id.tv_price,item?.all_price)
                ?.addOnClickListener(R.id.tv_one)
                ?.addOnClickListener(R.id.tv_two)
                ?.addOnClickListener(R.id.tv_three)
        helper?.getView<ImageView>(R.id.iv_head)?.loadImag(item?.pt_imgs!!,plach = R.mipmap.img_face)

        var two = helper?.getView<TextView>(R.id.tv_two)
        var three = helper?.getView<TextView>(R.id.tv_one)

        if (item?.pt_stat == 1 || item?.pt_stat == 7){
            three?.visibility = View.INVISIBLE
            if (item?.boss_comments == 0){
                two?.visibility = View.VISIBLE
                two?.text = "评价工人"
            }else{
                two?.visibility = View.INVISIBLE
            }
        }else{
            setTxtBtn(item?.pt_stat!!,two!!,three!!)
        }
    }

    fun setTxtBtn(pt_stat : Int,two:TextView,three:TextView){
        three.visibility = View.VISIBLE
        two.visibility = View.VISIBLE
        when(pt_stat){
            2 -> two.visibility = View.GONE
            3 -> two.text = "确认工人"
            4 -> two.text = "付收款"
            5 -> two.text = "付二期款"
            6 -> two.text = "付尾款"
        }
    }

}