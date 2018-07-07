package com.weibiaogan.litong.adapter.receipt

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.MyReceiptBean

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class MyReceiptAdapter(datas : List<MyReceiptBean>) : BaseQuickAdapter<MyReceiptBean,BaseViewHolder>(R.layout.view_my_receipt_item,datas){
    override fun convert(helper: BaseViewHolder?, item: MyReceiptBean?) {

    }
}