package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.blankj.utilcode.util.EncodeUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.BosIdentyContract
import com.weibiaogan.litong.mvp.model.BosIdentyModel
import com.weibiaogan.litong.utils.showToast
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * author: xiaoguagnfei
 * date: 2018/7/12
 * describe:
 */
class BosIdentyPresenter :BosIdentyContract.Presenter() {
    override fun bosIdenty(map: Map<String, String>) {
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().bosIdenty(userId,token,map).ui({
            getView()?.identySucceful()
            Log.i("fdfdf",it.msg)

        },{
            getView()?.showToast(it)
        })

    }


    override fun createModel(): BosIdentyContract.Model =BosIdentyModel()

    override fun fileStore(file: File?) {
        Observable.just(file).subscribeOn(Schedulers.io()).map {
            EncodeUtils.base64Encode2String(file?.readBytes())
        }.ui({
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
}