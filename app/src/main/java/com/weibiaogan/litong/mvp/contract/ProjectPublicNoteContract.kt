package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ProjectPublicNoteBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
interface ProjectPublicNoteContract {
    interface View :BaseMvpView{
          fun  getNote(projectPublicNoteBean: ProjectPublicNoteBean)
    }
    interface Model{
        fun note(userId:String,token:String):Observable<BaseResponseEntity<ProjectPublicNoteBean>>

    }
    abstract class Prsenter:BaseMvpPresenter<Model,View>(){
        abstract  fun note()
    }
}