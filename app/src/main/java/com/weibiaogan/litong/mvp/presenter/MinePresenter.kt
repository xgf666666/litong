package com.weibiaogan.litong.mvp.presenter

import com.blankj.utilcode.util.EncodeUtils
import com.tencent.mm.opensdk.utils.Log
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.MineContract
import com.weibiaogan.litong.mvp.model.MineModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我的酒馆
 */
class MinePresenter : MineContract.Presenter() {
    override fun addShop() {
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().addShop(userId,token).ui({
            if (it.status.equals("1"))
            getView()?.addShop(it.data?.system_content)
        },{
            Log.i("adfaggga",it)
            getView()?.showToast(it)
        })


    }

    override fun fileStore(file: File?) {
        Observable.just(file).subscribeOn(Schedulers.io()).map {

            EncodeUtils.base64Encode2String(file?.readBytes())
        }.ui({
            imgUp(it)
        }, {
            getView()?.showToast(it)
        })

    }


    private fun imgUp(imagBase64: String) {
        getModel().imgup(imagBase64).ui(
                {
                    getView()?.showToast("成功")
                },
                {
                    getView()?.showToast(it)
                })
//        AppApi.Api().imgup(imagBase64).ui(
//                {
//                    getView()?.showToast("成功")
//                }, {
//            getView()?.showToast(it)
//        }
//        )
    }

    override fun getData() {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
           getModel().UserIndex(userId, token)
                    .ui({
                        getView()?.setData(it.data)
                        Log.i("fgsgfgsg",it.data!!.user.toString())
                    }, {
                        getView()?.showToast(it)
                    })
        } else {
            getView()?.showToast("请先登录")
        }
    }

    override fun createModel(): MineContract.Model {
        return MineModel()
    }
}
