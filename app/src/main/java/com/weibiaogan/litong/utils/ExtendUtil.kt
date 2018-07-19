package com.weibiaogan.litong.utils

import android.content.Context
import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.home.HomeAdapter
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.ui.login.LoginActivity
import com.xx.baseuilibrary.BaseActivity
import com.xx.baseuilibrary.BaseFragment
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.exception.ApiFaileException
import com.xx.baseutilslibrary.network.exception.TokenInvalidException
import io.reactivex.Observable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

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

fun BaseMvpView.showToast(throwable:Throwable){
    var msg = ""

    if (throwable is HttpException) {
        val code = throwable.code()
        if (code == 500 || code == 404) {
            msg="服务器错误,请稍后重试"
        }
    } else if (throwable is ConnectException) {
        //断开网络
    } else if (throwable is SocketTimeoutException) {
        msg="连接服务器超时,请稍后重试"
    } else if (throwable is ApiFaileException) {
        msg=throwable.message!!//接口请求状态为0的情况
    } else if (throwable is TokenInvalidException) {
        msg="状态异常，请重新登录！"//需要重新登录获取
        if (this is BaseActivity){
            tokenError((this as Context),msg)
        }else if (this is BaseFragment){
            tokenError((this as BaseFragment).context!!,msg)
        }else{
            showToast(msg)
        }
        return
    } else {
        msg="未知错误" + throwable.message
    }
    showToast(msg)
}


fun tokenError(context : Context,msg:String){
    var dialog = NormalDialog(context)
    dialog.style(NormalDialog.STYLE_TWO)
            .content(msg)
            .title("提示")
            .style(NormalDialog.STYLE_TWO)
            .contentTextSize(17f)
            .titleTextSize(17f)
            .contentTextColor(context.resources.getColor(R.color.color888888))
            .titleTextColor(context.resources.getColor(R.color.color222222))
            .btnNum(1)
            .btnText("确定")
            .btnTextColor(context.resources.getColor(R.color.color3078EF))
            .setCanceledOnTouchOutside(false)
    dialog.show()
    dialog.setOnBtnClickL(OnBtnClickL {
        dialog.dismiss()
        Constants.loginOut()
        context.startActivity(Intent(context, LoginActivity::class.java))
    })
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