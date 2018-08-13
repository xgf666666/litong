package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ImageBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.util.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
interface ShopAddContract {
    interface View:BaseMvpView{
        fun addSucceeful(msg:String)
        fun setView(msg:String)
    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun addShop(map:HashMap<String,String>)



    }

    interface Model{
        fun addShop(userId:String,token:String,map:HashMap<String,String>): Observable<BaseResponseEntity<Any>>
        fun imgup(imagBase64:String): Observable<BaseResponseEntity<ImageBean>>

    }
}