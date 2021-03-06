package com.weibiaogan.litong.mvp.presenter

import android.text.TextUtils
import android.util.Log
import com.blankj.utilcode.util.EncodeUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.MyIntroContract
import com.weibiaogan.litong.mvp.model.MyIntroModel
import com.weibiaogan.litong.utils.showToast
import com.xx.baseutilslibrary.network.exception.ServerFaileException
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.io.File

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:我的信息
 */
class MyIntroPresenter :  MyIntroContract.Presenter() {

    override fun getUserData(){

        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().UserIndex(userId, token)
                    .ui({
                        getView()?.setData(it.data)
                    }, {
                        getView()?.showToast(it)
                    })
        }else{
            getView()?.showToast("请先登录")

        }
    }


    override fun updateUser(sex: String) {
        if (TextUtils.isEmpty(sex)) {
            getView()?.showToast("性别不能为空")
            return
        }

        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            val map = mapOf("user_sex" to sex)
            getModel().updateUser("$userId", token, sex)
                    .loadDefulat(getView()!!)
                    .ui({
                        if (it.code!!.toInt()>1000){
                            throw ServerFaileException(it.code!!)
                        }else{
//                            getView()?.successful()
                        }
                    }, {
                        getView()?.showToast(it)
                    })
        } else {
            getView()?.showToast("请先登录")
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
        getModel().imgup(imagBase64).ui(
                {
                    Log.i("qqqqq",it.msg+"地址"+it?.data?.imgUrl)
                 updateUserImage(it?.data?.imgUrl)
                }, {
            getView()?.showToast(it)
        }
        )
    }

    private  fun updateUserImage(imageUrl: String?) {
        if (TextUtils.isEmpty(imageUrl)) {
            getView()?.showToast("图片地址不能为空")
            return
        }
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            val map = mapOf("user_img" to imageUrl)
            getModel().updateUser(userId, token,imageUrl!!)
                    .loadDefulat(getView()!!)
                    .ui({

                        getView()?.successful(imageUrl)
                    }, {
                        getView()?.showToast(it)
                    })
        } else {
            getView()?.showToast("请先登录")
        }
    }
     fun updateUserSex(sex:String){
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().updateUserSex(userId,token,sex).ui({
            getView()?.showToast("修改成功")
            getView()?.sexSuccessful()
        },{
            getView()?.showToast(it)
        })
    }
    //退出登录
    fun loginOff(){
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().loginOff(userId,token).ui({
            if (it.status.equals("1")){
                getView()?.loginOff()
            }
        },{

        })
    }


    override fun createModel(): MyIntroContract.Model {
        return MyIntroModel()
    }
}
