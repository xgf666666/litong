package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.StoreListBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface StoreListConstract {

    public interface View : BaseMvpView {
        fun getStoreListData(data : List<StoreListBean>)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun storeList(page : String ,lat : String ,lng : String)
    }

    public interface Model {
        fun storeList(userId : String, token : String, page : String ,lat : String ,lng : String) : Observable<BaseResponseEntity<List<StoreListBean>>>
    }
}