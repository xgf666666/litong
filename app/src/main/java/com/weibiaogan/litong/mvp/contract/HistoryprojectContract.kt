package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ProjectBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface HistoryprojectContract {
    interface View :BaseMvpView{
        fun getProjectBean(data : List<ProjectBean>)
    }
     abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun historyProject(stat : String , lat : String ,lng : String, page : String ,type : String)
    }
    interface Model{
        fun historyProject(userId : String, token : String,stat : String , lat : String ,lng : String, page : String ,type : String) : Observable<BaseResponseEntity<List<ProjectBean>>>
    }
}