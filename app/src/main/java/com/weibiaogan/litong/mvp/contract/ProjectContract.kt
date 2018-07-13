package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.entity.IsPublic
import com.weibiaogan.litong.entity.PublicWorker
import com.weibiaogan.litong.entity.Worker
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.io.File

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:发布项目
 */
interface ProjectContract {
    interface View : BaseMvpView {
        fun successful()
        fun setView(file:String)
        fun setWorker(publicWorker: PublicWorker)
        fun isPublic(isPublic: BaseResponseEntity<IsPublic>)
    }

    abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun pullProject( ptname: String, ptdescribe: String,
                                 endtime: String, ptaddress: String, areaid: String, firstprice: String,
                                 secondprice: String, threeprice: String, ptimgs: String, latlong: String,
                                 allprice: String)
        abstract  fun fileStore(file: File?)
        abstract fun getWorkerTyle()
        abstract fun isPublic()
    }

    interface Model {
        fun pullProject(userid: String, token: String, ptname: String, ptdescribe: String,
                        endtime: String, ptaddress: String, areaid: String, firstprice: String,
                        secondprice: String, threeprice: String, ptimgs: String, latlong: String,
                        allprice: String): Observable<BaseResponseEntity<List<Any>>>
        fun imgup(imagBase64:String):Observable<BaseResponseEntity<ImageBean>>
        fun  getWorkerTyle(userId:String,token:String): Observable<BaseResponseEntity<PublicWorker>>
        fun isPublic(userId:String,token:String):Observable<BaseResponseEntity<IsPublic>>

    }
}
