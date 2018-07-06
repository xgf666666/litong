package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.UserCenterBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:账户
 */
interface WalletContract {

    interface View : BaseMvpView{
        fun onRefreshBalanceSuccess(entity: UserCenterBean)
    }

    abstract class Presenter: BaseMvpPresenter<Model, View>() {
        abstract  fun refreshBalance(userId : String,token : String)
    }

    interface Model {
        fun refreshBalance(userId : String,token : String,httpObserver: XxBaseHttpObserver<UserCenterBean>)
    }
}
