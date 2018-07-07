package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.WorkerIdentyContract
import com.weibiaogan.litong.mvp.model.WorkerIdentyModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class WorkerIdentyTwoPresenter:WorkerIdentyContract.Presenter() {
    override fun createModel(): WorkerIdentyContract.Model =WorkerIdentyModel()
}