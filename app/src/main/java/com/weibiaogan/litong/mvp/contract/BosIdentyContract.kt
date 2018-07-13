package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ImageBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.io.File

/**
 * author: xiaoguagnfei
 * date: 2018/7/12
 * describe:
 */
interface BosIdentyContract {
    interface View :BaseMvpView{
        fun setView(data:String)
        fun identySucceful()
    }
    interface Model {
        fun bosIdenty(userId:String ,token:String,map: Map<String,String>): Observable<BaseResponseEntity<Any>>
        fun imgup(imagBase64:String):Observable<BaseResponseEntity<ImageBean>>
    }
    abstract class Presenter :BaseMvpPresenter<Model,View>(){
        abstract fun bosIdenty(map: Map<String,String>)
        abstract  fun fileStore(file: File?)
    }
}