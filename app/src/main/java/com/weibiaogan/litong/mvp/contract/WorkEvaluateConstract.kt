package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.entity.WorkEvaluateBean
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
interface WorkEvaluateConstract {

    public interface View : BaseMvpView {
        fun getWorkEvaluateData(data : WorkEvaluateBean)
    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun workEvaluate(work_user_id: String,page : String)
        abstract fun projectEvaluate(work_user_id: String,page : String)
    }

    public interface Model {
        fun workEvaluate(userId : String , token : String , work_user_id : String,page : String) : Observable<BaseResponseEntity<WorkEvaluateBean>>
        fun projectEvaluate(userId : String , token : String , pt_user_id : String,page : String) : Observable<BaseResponseEntity<WorkEvaluateBean>>
    }
}