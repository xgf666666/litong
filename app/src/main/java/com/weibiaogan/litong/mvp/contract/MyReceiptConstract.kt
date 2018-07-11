package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.MyReceiptBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface MyReceiptConstract {

    public interface View : BaseMvpView {
        fun getProjectList(data : List<MyReceiptBean>)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun workProjectList(stat: String,page : String)
    }

    public interface Model {
        fun workProjectList(userId : String,token : String,stat: String,page : String) : Observable<BaseResponseEntity<List<MyReceiptBean>>>
    }
}