package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.HomeBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:首页
 */
interface HomeConstract {

    public interface View : BaseMvpLcecView<Any> {

    }

    public abstract class Presenter : BaseMvpPresenter<Model, View>() {
        abstract fun getHomeData(page : String , lat : String , lnt : String)
    }

    public interface Model {

        fun getHomeData(page : String , lat : String , lnt : String)

    }
}
