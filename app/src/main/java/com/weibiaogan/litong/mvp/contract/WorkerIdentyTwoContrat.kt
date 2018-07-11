package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.Worker
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface WorkerIdentyTwoContrat {
    interface View:BaseMvpView{
       fun getListData(data:List<Worker>)
        fun succeful()

    }
    interface Model{
        fun  getWorkerTyle(userId:String,token:String): Observable<BaseResponseEntity<List<Worker>>>
        fun renZhengWork(userId:String,token:String,map: Map<String,String>):Observable<BaseResponseEntity<Any?>?>?

    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun getWorkerTyle()
        abstract fun renZhengWork(map: Map<String,String>)

    }
}