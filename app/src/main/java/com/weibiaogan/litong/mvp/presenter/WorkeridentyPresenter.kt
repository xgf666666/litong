package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.blankj.utilcode.util.EncodeUtils
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.WorkerIdentyContract
import com.weibiaogan.litong.mvp.model.WorkerIdentyModel
import com.weibiaogan.litong.utils.showToast
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
            imgUp(it)

        }, {
            getView()?.showToast(it)
        }
        )
    }
    private fun imgUp(imagBase64: String) {
        getModel().imgup(imagBase64).ui(
                {
                    Log.i("qqqqq",it.msg+"地址"+it?.data?.imgUrl)
                    getView()?.setView(it?.data?.imgUrl!!)
                }, {
            getView()?.showToast(it)
        }
        )
    }


    override fun createModel(): WorkerIdentyContract.Model =WorkerIdentyModel()
}