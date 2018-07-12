package com.weibiaogan.litong.mvp.presenter

import com.blankj.utilcode.util.EncodeUtils
import com.tencent.mm.opensdk.utils.Log
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkerIdentyContract
import com.weibiaogan.litong.mvp.model.WorkerIdentyModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * author: xiaoguagnfei
 * date: 2018/7/7
 * describe:
 */
class WorkeridentyPresenter:WorkerIdentyContract.Presenter() {
    override fun fileStore(file: File?) {
        Observable.just(file).subscribeOn(Schedulers.io()).map {

            EncodeUtils.base64Encode2String(file?.readBytes())
        }.ui({
            Log.i("qqqqq",it)
            getView()?.setView(it)
        }, {
            getView()?.showToast(it)
        }
        )

    }

    override fun createModel(): WorkerIdentyContract.Model =WorkerIdentyModel()
}