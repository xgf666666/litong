package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.SureWorkContract
import com.weibiaogan.litong.mvp.model.SureWorkModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class SureWorkerPresenter:SureWorkContract.Presenter() {
    override fun createModel(): SureWorkContract.Model =SureWorkModel()
}