package com.weibiaogan.litong.ui.blacklist

import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
interface BlacklistContract {
    interface View : BaseMvpLcecView<List<Any>> {



        fun addData(data: List<Any>?)
    }

    abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun getData(isRefresh: Boolean, page: Int)

        abstract fun loadData()
    }

    interface Model {
        fun getData(userId: String, token: String, status: String): Observable<BaseResponseEntity<List<Any>>>

    }
}
