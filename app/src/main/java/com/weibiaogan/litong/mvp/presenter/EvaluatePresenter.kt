package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.blankj.utilcode.util.EncodeUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.EvaluateConstract
import com.weibiaogan.litong.mvp.model.EvaluateModel
import com.weibiaogan.litong.utils.showToast
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class EvaluatePresenter : EvaluateConstract.Presenter(){
    override fun evaluateWork(pt_id: String, com_content: String, com_imgs: String, score: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().evaluateWork(userId, token,pt_id, com_content, com_imgs, score)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.evaluateSuccess("评论成功")
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

    override fun evaluateBoss(pt_id: String, com_content: String, com_imgs: String, score: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().evaluateBoss(userId, token,pt_id, com_content, com_imgs, score)
                    ?.loadDefulat(getView()!!)
                    ?.ui({
                        getView()?.evaluateSuccess("评论成功")
                    }, {
                        getView()?.showToast(it)
                    })
        }
    }

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
        getModel().imgup(imagBase64)
                .loadDefulat(getView()!!)
                .ui(
                {
                    Log.i("qqqqq",it.msg+"地址"+it?.data?.imgUrl)
                    getView()?.getImgUrl(it.data?.imgUrl!!)
                    //updateUserImage(it?.data?.imgUrl)
                }, {
            getView()?.showToast(it)
        }
        )
    }

    override fun createModel(): EvaluateConstract.Model = EvaluateModel()
}