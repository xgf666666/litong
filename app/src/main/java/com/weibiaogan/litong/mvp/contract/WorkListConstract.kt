package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.entity.WorkListBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
interface WorkListConstract {

    public interface View : BaseMvpView {
        fun getWorkListData(data : List<WorkListBean>)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun workerList(page: String, lat: String, lng: String)
    }

    public interface Model {
        fun workerList(userId: String,token : String,
        page : String,lat : String , lng : String) : Observable<BaseResponseEntity<List<WorkListBean>>>
    }
}