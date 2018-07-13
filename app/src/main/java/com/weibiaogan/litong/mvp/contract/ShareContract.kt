package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ShareUserBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/13
 * describe:
 */
interface ShareContract {
    interface View :BaseMvpView{
        fun succeful(shareUserBean: ShareUserBean)

    }
    interface Model{
        fun share(userid :String,token:String):Observable<BaseResponseEntity<ShareUserBean>>


    }
    abstract class Presenter:BaseMvpPresenter<Model,View>(){
        abstract fun share()

    }
}