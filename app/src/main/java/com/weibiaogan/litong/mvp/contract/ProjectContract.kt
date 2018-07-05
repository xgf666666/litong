package com.weibiaogan.litong.mvp.contract

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:发布项目
 */
interface ProjectContract {
    interface View : BaseMvpView {
        fun successful()
    }

    abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun pullProject( ptname: String, ptdescribe: String,
                                 endtime: String, ptaddress: String, areaid: String, firstprice: String,
                                 secondprice: String, threeprice: String, ptimgs: String, latlong: String,
                                 allprice: String)
    }

    interface Model {
        fun pullProject(userid: String, token: String, ptname: String, ptdescribe: String,
                        endtime: String, ptaddress: String, areaid: String, firstprice: String,
                        secondprice: String, threeprice: String, ptimgs: String, latlong: String,
                        allprice: String): Observable<BaseResponseEntity<List<Any>>>


    }
}
