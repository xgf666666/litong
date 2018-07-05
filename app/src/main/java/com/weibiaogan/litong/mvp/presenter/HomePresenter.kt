package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.weibiaogan.litong.mvp.model.HomeModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:首页
 */
class HomePresenter : HomeConstract.Presenter() {





    override fun createModel(): HomeConstract.Model {
        return HomeModel()
    }
}
