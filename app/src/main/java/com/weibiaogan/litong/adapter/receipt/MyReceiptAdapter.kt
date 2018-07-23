package com.weibiaogan.litong.adapter.receipt

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.MyReceiptBean
import com.weibiaogan.litong.extensions.loadImag

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class MyReceiptAdapter(datas : List<MyReceiptBean>) : BaseQuickAdapter<MyReceiptBean,BaseViewHolder>(R.layout.view_my_receipt_item,datas){
    /**
     * pt_stat  状态 1是已取消 2是已审核上架 3是用户接单中 4是用户成功接单 5是支付一期款 6是支付二期款 7是支付三期款
    当pt_stat=1,则项目出现在完成列表，需求方列表里，如果worker_comments=0，则出现评论按钮，worker_comments=1，则没有。‘查看项目按钮’一直有。

    当pt_stat=7,则项目出现在完成列表，需求方列表里，如果worker_comments=0，则出现评论按钮，worker_comments=1，则没有。‘查看项目按钮’一直有。
     */
    override fun convert(helper: BaseViewHolder?, item: MyReceiptBean?) {
        helper?.setText(R.id.tv_my_receipt_name,item?.pt_name)
                ?.setText(R.id.tv_my_receipt_price,item?.all_price)
                ?.addOnClickListener(R.id.tv_my_receipt_evaluate)
                ?.addOnClickListener(R.id.tv_my_receipt_look)
        helper?.getView<ImageView>(R.id.iv_my_receipt_img)?.loadImag(item?.pt_imgs!!,plach = R.mipmap.img_default)

        setViewTxt(helper,item)
    }

    fun setViewTxt(helper: BaseViewHolder?, item: MyReceiptBean?){
        helper?.setText(R.id.tv_my_receipt_title,item?.pt_stat_detail)
        if (item?.pt_stat == 1 || item?.pt_stat == 7){
            if (item?.worker_comments == 0){
                helper?.getView<View>(R.id.tv_my_receipt_evaluate)?.visibility = View.VISIBLE
            }else{
                helper?.getView<View>(R.id.tv_my_receipt_evaluate)?.visibility = View.GONE
            }
        }else{
            helper?.getView<View>(R.id.tv_my_receipt_evaluate)?.visibility = View.GONE
        }
    }
}