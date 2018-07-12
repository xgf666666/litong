package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ProjectBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我要接单
 */
interface OrdersContract {
    interface View : BaseMvpLcecView<List<ProjectBean>> {


    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {
        abstract fun historyProject(stat : String, page : String ,type : String)
    }

    interface Model {

        fun historyProject(userId : String, token : String,stat : String , lat : String ,lng : String, page : String ,type : String) : Observable<BaseResponseEntity<List<ProjectBean>>>
    }
}
