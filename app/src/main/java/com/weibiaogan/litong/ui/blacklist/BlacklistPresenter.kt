package com.weibiaogan.litong.ui.blacklist

import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.ui.blacklist.BlacklistContract
import com.weibiaogan.litong.ui.blacklist.BlacklistModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
class BlacklistPresenter :  BlacklistContract.Presenter() {
    override fun getData() {
        getModel().getData().ui({},{})
    }

    override fun createModel(): BlacklistContract.Model {
        return BlacklistModel()
    }
}
