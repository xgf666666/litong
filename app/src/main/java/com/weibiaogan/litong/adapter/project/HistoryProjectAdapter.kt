package com.weibiaogan.litong.adapter.project

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.Project

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class HistoryProjectAdapter(data:ArrayList<Project>) :BaseQuickAdapter<Project,BaseViewHolder>(R.layout.item_historyproject,data){
    /**
     *
     */
    override fun convert(helper: BaseViewHolder?, item: Project?) {

    }
}