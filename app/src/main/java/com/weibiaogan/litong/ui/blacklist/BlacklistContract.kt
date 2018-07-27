package com.weibiaogan.litong.ui.blacklist

import com.weibiaogan.litong.entity.BlackBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
interface BlacklistContract {
    interface View : BaseMvpView {
        fun addData(data: List<BlackBean>)
        fun delSuccess(msg:String)
    }

    abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun getData(page: Int)
        abstract fun delBack(id:String)
    }

    interface Model {
        fun getData(userId: String, token: String, status: String,lat:String,lng:String): Observable<BaseResponseEntity<List<BlackBean>>>
        fun delBack(userId: String,token: String,id: String) : Observable<BaseResponseEntity<Any?>?>?

    }
}
