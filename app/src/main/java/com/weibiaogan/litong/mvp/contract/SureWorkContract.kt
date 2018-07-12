package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.WorkDetailBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface SureWorkContract {
    interface View:BaseMvpView{
        fun sureWork(msg : String)
        fun getWorkDetailData(bean : WorkDetailBean)
    }
    interface Model{
        fun bindWork(userId : String,token : String,pt_id : String) : Observable<BaseResponseEntity<Any?>?>?
        fun dieWork(userId : String,token : String,pt_id : String) : Observable<BaseResponseEntity<Any?>?>?
        fun workDetail(userId : String,token : String,worker_user_id : String,lat : String, lnt : String) : Observable<BaseResponseEntity<WorkDetailBean>>
    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun bindWork(pt_id : String)
        abstract fun dieWork(pt_id : String)
        abstract fun workDetail(worker_user_id : String)
    }
}