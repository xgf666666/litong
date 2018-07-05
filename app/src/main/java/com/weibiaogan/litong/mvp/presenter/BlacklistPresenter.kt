package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.BlacklistContract
import com.weibiaogan.litong.mvp.model.BlacklistModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
class BlacklistPresenter :  BlacklistContract.Presenter() {
    override fun createModel(): BlacklistContract.Model {
        return BlacklistModel()
    }
}
