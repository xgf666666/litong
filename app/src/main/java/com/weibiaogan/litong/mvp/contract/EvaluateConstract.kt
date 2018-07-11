package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.ImageBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.io.File

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface EvaluateConstract {

    public interface View : BaseMvpView {
        fun evaluateSuccess(msg : String)
        fun getImgUrl(url : String)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun evaluateWork(pt_id : String , com_content : String ,com_imgs : String, score : String)
        abstract fun evaluateBoss(pt_id : String , com_content : String ,com_imgs : String, score : String)
        abstract fun fileStore(file: File?)
    }

    public interface Model {
        fun evaluateWork(userId : String, token : String,pt_id : String , com_content : String ,com_imgs : String, score : String) : Observable<BaseResponseEntity<Any?>?>?
        fun evaluateBoss(userId : String, token : String,pt_id : String , com_content : String ,com_imgs : String, score : String) : Observable<BaseResponseEntity<Any?>?>?
        fun imgup(imagBase64: String): Observable<BaseResponseEntity<ImageBean>>
    }
}