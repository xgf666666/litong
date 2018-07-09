package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.entity.StoreDetailBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface StoreDetailConstract {

    public interface View : BaseMvpView {
        fun getStoreDetailData(bean : StoreDetailBean)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun storeDetail(store_id : String ,lat : String ,lng : String)
    }

    public interface Model {
        fun storeDetail(userId : String, token : String, store_id : String ,lat : String ,lng : String) : Observable<BaseResponseEntity<StoreDetailBean>>
    }
}