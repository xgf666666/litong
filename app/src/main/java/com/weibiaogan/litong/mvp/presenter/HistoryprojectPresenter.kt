package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.HistoryprojectContract
import com.weibiaogan.litong.mvp.model.HistoryProjectModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class HistoryprojectPresenter:HistoryprojectContract.Presenter() {
    override fun createModel(): HistoryprojectContract.Model =HistoryProjectModel()
}