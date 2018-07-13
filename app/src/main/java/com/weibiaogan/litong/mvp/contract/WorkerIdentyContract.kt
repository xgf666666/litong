package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ImageBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.io.File

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface WorkerIdentyContract {
    interface View :BaseMvpView{
        fun setView(file: String)

    }
    interface Model{
        fun imgup(imagBase64:String): Observable<BaseResponseEntity<ImageBean>>
    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun fileStore(file: File?)

    }
}