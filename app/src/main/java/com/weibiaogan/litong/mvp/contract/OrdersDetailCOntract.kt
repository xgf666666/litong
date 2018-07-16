package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.OrderDetailBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:项目详情
 */
interface OrdersDetailCOntract {
    interface View : BaseMvpView{
        fun requestSuccess(msg:String)
        fun getOrderDetail(bean : OrderDetailBean)
    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {
        abstract fun orderDetail(pt_id : String)
        abstract fun addBlack(worker_user_id: String)
        abstract fun clickReceipt(pt_id: String)
    }

    interface Model {

        fun orderDetail(userId : String,token : String,pt_id : String) : Observable<BaseResponseEntity<OrderDetailBean>>
        fun addBlack(userId : String,token : String,worker_user_id:String) : Observable<BaseResponseEntity<Any?>?>?
        fun clickReceipt(userId : String,token : String,pt_id:String) : Observable<BaseResponseEntity<Any?>?>?
    }
}