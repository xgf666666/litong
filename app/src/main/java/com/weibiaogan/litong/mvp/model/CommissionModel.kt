package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.App
import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.Withdraw
import com.weibiaogan.litong.mvp.contract.CommissionContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:佣金金额
 */
class CommissionModel : CommissionContract.Model {
    override fun getData(userId: String, token: String)= AppApi.Api().getContent(userId,token)


    override fun withdrawAdd(userId: String, token: String?, cash: String, account: String, type: String, balance_payment: String) =
            AppApi.Api().withdrawAdd(userId, token, cash, account, type, balance_payment)




}
