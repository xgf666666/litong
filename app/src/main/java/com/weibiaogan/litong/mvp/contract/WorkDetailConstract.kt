package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.entity.WorkDetailBean
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
interface WorkDetailConstract {

    public interface View : BaseMvpView {
        fun getWorkDetailData(bean : WorkDetailBean)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun workDetail(worker_user_id : String)
    }

    public interface Model {
        fun workDetail(userId : String,token : String,worker_user_id : String,lat : String, lnt : String) : Observable<BaseResponseEntity<WorkDetailBean>>
    }
}