package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.ModifyBindContract
import com.weibiaogan.litong.mvp.model.ModifyBindModel
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:修改绑定手机
 */
class ModifyBindPresenter : ModifyBindContract.Presenter() {
    override fun getData() {
        getModel().getData(1, object : XxBaseHttpObserver<MutableList<Any>>() {
            override fun onCompleted(msg: String?, entity: MutableList<Any>?) {

            }

            override fun onError(error: String?) {
            }

            override fun onStart() {
            }

            override fun onFinish() {
            }


            /*
            *
            * java.lang.NoSuchMethodError: No direct method <init>(Ljava/util/ArrayList;Landroid/support4/app/FragmentManager;)V in class Lcom/micropole/usdmall/adapter/TabAdapter; or its super classes (declaration of 'com.micropole.usdmall.adapter.TabAdapter' appears in /data/app/com.micropole.usdmall-1/base.apk)
        at com.micropole.usdmall.MainActivity.initViewPager(MainActivity.kt:26)*/

        })
    }

    override fun createModel(): ModifyBindContract.Model {
        return ModifyBindModel()
    }
}

