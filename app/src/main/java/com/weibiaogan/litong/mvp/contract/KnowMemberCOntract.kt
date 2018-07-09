package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.MemberBean
import com.weibiaogan.litong.entity.MemberpowrBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
interface KnowMemberCOntract {
    interface View :BaseMvpView{
        fun setView(memberpowrBean: MemberpowrBean)

    }
    interface Model{
        fun getvipContent(userId:String,token:String): Observable<BaseResponseEntity<MemberpowrBean>>

    }
    abstract class Prsenter:BaseMvpPresenter<Model,View>(){
        abstract  fun getvipContent()

    }
}