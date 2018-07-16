package com.weibiaogan.litong.utils

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.weibiaogan.litong.adapter.home.HomeAdapter
import com.weibiaogan.litong.extensions.loadDefulat
import com.xx.baseuilibrary.mvp.BaseMvpView
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/10
 * describe:
 */

/**
 *  刷新加载时添加数据
 */
fun <T> SmartRefreshLayout.addData(adapter : BaseQuickAdapter<T,BaseViewHolder>,datas : List<T>){
    if (this.refreshHeader !is ClassicsHeader){
        this.setRefreshHeader(com.scwang.smartrefresh.layout.header.ClassicsHeader(context))
    }
    if (this.refreshFooter !is ClassicsFooter){
        this.setRefreshFooter(com.scwang.smartrefresh.layout.footer.ClassicsFooter(context))
    }
    if (this.isLoading){
        this.finishLoadMore()
        if (datas.isEmpty()){
            this.isEnableLoadMore = false
        }else{
            if (adapter is HomeAdapter) adapter.addHomeData(this@addData,datas) else adapter.addData(datas)
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
            adapter.setNewData(arrayListOf())
            this.isEnableLoadMore = false
            this.isEnableRefresh = false
        }

    }
}

fun <T> HomeAdapter.addHomeData(refreshLayout: SmartRefreshLayout, datas: List<T>){
    var project = (datas as List<HomeAdapter.HomeMultiItem>)[0].bean.project
    if (project != null && project.size > 0){
        refreshLayout.postDelayed({ addHomeData(project) },1000)
    }else{
        addData(arrayListOf())
        refreshLayout.isEnableLoadMore = false
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

/**
 * 加载弹框
 */
fun <T> Observable<T>.loadDefulatRefresh(show:Boolean,view: BaseMvpView) : Observable<T>{
    if (show){
        return loadDefulat(view)
    }
    return this
}