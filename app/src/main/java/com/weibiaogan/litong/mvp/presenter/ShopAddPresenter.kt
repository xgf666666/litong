package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.ShopAddContract
import com.weibiaogan.litong.mvp.model.ShopAddModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class ShopAddPresenter:ShopAddContract.Presenter() {
    override fun createModel(): ShopAddContract.Model =ShopAddModel()
}