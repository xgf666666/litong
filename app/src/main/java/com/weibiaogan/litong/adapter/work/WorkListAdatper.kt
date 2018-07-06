package com.weibiaogan.litong.adapter.work

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.WorkListBean

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class WorkListAdatper(workList : List<WorkListBean?>) : BaseQuickAdapter<WorkListBean,BaseViewHolder>(R.layout.work_list_item,workList){
    override fun convert(helper: BaseViewHolder?, item: WorkListBean?) {

    }


}