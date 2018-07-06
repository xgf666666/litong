package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.mvp.contract.WalletContract
import com.xx.baseutilslibrary.network.rx.RxHelper
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:账户
 */
class WalletModel : WalletContract.Model {
    override fun refreshBalance(userId: String, token: String, httpObserver: XxBaseHttpObserver<UserCenterBean>) {
        AppApi.Api().UserIndex(userId,token)
                .compose(RxHelper.io_main())
                .compose(RxHelper.start_finish(httpObserver))
                .subscribe(httpObserver)
    }

}
