package com.weibiaogan.litong.utils

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.activity_work_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/10
 * describe:
 */

/**
 *  刷新加载时添加数据
 */
fun <T> SmartRefreshLayout.addData(adapter : BaseQuickAdapter<T,BaseViewHolder>,datas : List<T>){
    if (this.isLoading){
        adapter.addData(datas)
        this.finishLoadMore()
        if (datas.isEmpty()){
            this.isEnableLoadMore = false
        }
    }else if (this.isRefreshing){
        adapter.setNewData(datas)
        this.finishRefresh()
        this.isEnableLoadMore = true
    }else{
        if (datas.isNotEmpty()){
            adapter.setNewData(datas)
            this.isEnableLoadMore = true
            this.isEnableRefresh = true
        }else{
            this.isEnableLoadMore = false
            this.isEnableRefresh = false
        }

    }
}

/**
 * m 转换 km
 */
fun Int.changeKm() : String{
    if (this >= 1000){
        return (this / 1000).toString() + "km"
    }else{
        return this.toString() + "m"
    }
}