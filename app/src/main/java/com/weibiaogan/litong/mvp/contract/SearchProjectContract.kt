package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.SearchProjectBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我要接单
 */
interface SearchProjectContract {
    interface View : BaseMvpView {
        fun getSearchResult(data : List<SearchProjectBean>)

    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {
        abstract fun searchProject(pt_name : String , page : String)
    }

    interface Model {
        fun searchProject(pt_name : String , page : String , lat : String , lng : String) : Observable<BaseResponseEntity<List<SearchProjectBean>>>
    }
}
