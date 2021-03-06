package com.weibiaogan.litong.mvp.presenter

import android.util.Log
import com.blankj.utilcode.util.EncodeUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.ProjectContract
import com.weibiaogan.litong.mvp.model.ProjectModel
import com.weibiaogan.litong.utils.showToast
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:发现
 */
class ProjectPresenter : ProjectContract.Presenter() {
    override fun isPublic() {
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().isPublic(userId,token).ui({
            getView()?.isPublic(it)
        },{
            getView()?.showToast(it)
        })
    }

    override fun getWorkerTyle() {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().getWorkerTyle(userId,token)?.ui({
                getView()?.setWorker(it.data!!)
                Log.i("fafafaf",it.data.toString())
            },{
                getView()?.showToast(it)
            })


    }

    override fun pullProject(ptname: String, ptdescribe: String,
                             endtime: String, ptaddress: String, areaid: String, firstprice: String,
                             secondprice: String, threeprice: String, ptimgs: String, latlong: String,
                             allprice: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            if (ptname.isNullOrEmpty()||ptdescribe.isNullOrEmpty()||endtime.isNullOrEmpty()||ptaddress.isNullOrEmpty()
            ||areaid.equals("0")||firstprice.isNullOrEmpty()||secondprice.isNullOrEmpty()||allprice.isNullOrEmpty() ){
                getView()?.showToast("请填写完整的项目信息")
                return
            }
//            if (ptname.isNullOrEmpty()) {
//                getView()?.showToast("项目名不能为空")
//                return
//            }
//            if (ptdescribe.isNullOrEmpty()) {
//                getView()?.showToast("项目描述不能为空")
//                return
//            }
//            if (endtime.isNullOrEmpty()) {
//                getView()?.showToast("项目结束时间不能为空")
//                return
//            }
//            if (ptaddress.isNullOrEmpty()) {
//                getView()?.showToast("项目地址不能为空")
//                return
//            }
//            if (areaid.equals("0")) {
//                getView()?.showToast("工人类型不能为空")
//                return
//            }
//            if (firstprice.isNullOrEmpty()) {
//                getView()?.showToast("项目首付比例不能为空")
//                return
//            }
//            if (secondprice.isNullOrEmpty()) {
//                getView()?.showToast("项目二期比例不能为空")
//                return
//            }
            if (!(firstprice.toInt()+secondprice.toInt()<=99)) {
                getView()?.showToast("首款跟二期款比例加起来要小于100%")
                return
            }

//            if (allprice.isNullOrEmpty()) {
//                getView()?.showToast("项目金额不能为空")
//                return
//            }
            if (ptimgs.isNullOrEmpty()){
                getView()?.showToast("请上传项目图片")
                return
            }

            getModel().pullProject(userId, token, ptname, ptdescribe, endtime, ptaddress, areaid, firstprice, secondprice, threeprice, ptimgs, latlong, allprice)
                    .loadDefulat(getView()!!)
                    .ui({
                        getView()?.successful(it.data!!)
                        Log.i("dagag",it.msg)
                    }, {
                        getView()?.showToast(it)
                    })
        } else {

        }
    }
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

    override fun createModel(): ProjectContract.Model {
        return ProjectModel()
    }
}
