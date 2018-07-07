package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.MyPublishProjectContract
import com.weibiaogan.litong.mvp.model.MyPublishProjectModel
import com.xx.baseuilibrary.mvp.BaseMvpPresenter

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:
 */
class MyPublishProjectPresenter : MyPublishProjectContract.Presenter() {
    override fun createModel(): MyPublishProjectContract.Model =MyPublishProjectModel()
}