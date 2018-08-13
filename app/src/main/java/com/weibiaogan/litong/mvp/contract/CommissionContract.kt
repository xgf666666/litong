package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.Withdraw
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:佣金金额
 */
interface CommissionContract {
    interface View : BaseMvpView {
        fun successful()
        fun getData(data:String)

    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {
        abstract    fun getData()

        abstract    fun commission(cash: String, account: String,type:String,balancePayment:String)
    }

    interface Model {
        fun getData(userId: String, token: String):Observable<BaseResponseEntity<Withdraw>>
        fun withdrawAdd(userId: String, token: String?, cash: String, account: String, type: String,
                        balance_payment: String): Observable<BaseResponseEntity<Any>>

    }
}
