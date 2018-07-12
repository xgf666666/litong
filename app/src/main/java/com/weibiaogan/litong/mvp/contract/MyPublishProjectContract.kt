package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.PublicProjectBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:  我的发布
 */
interface MyPublishProjectContract {
    interface View : BaseMvpView {
        fun getBossProjectList(data : PublicProjectBean)
        fun cancelProject(msg : String)
    }
    abstract class Presenter : BaseMvpPresenter<Model, View>(){
        abstract fun bossProjectList(stat: String,page : String)
        abstract fun cancelProject(pt_id:String)
    }
    interface Model{
        fun bossProjectList(userId : String,token : String,stat: String,page : String) : Observable<BaseResponseEntity<PublicProjectBean>>
        fun cancelProject(userId : String,token : String,pt_id : String) : Observable<BaseResponseEntity<Any?>?>?
    }
}