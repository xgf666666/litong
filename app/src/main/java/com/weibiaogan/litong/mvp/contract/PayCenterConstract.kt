package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.MemberBean
import com.xx.baseuilibrary.mvp.BaseMvpPresenter
import com.xx.baseuilibrary.mvp.BaseMvpView
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable
import java.util.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:支付中心
 */
interface PayCenterConstract {
    interface View :BaseMvpView{
       fun setView(memberBean: MemberBean)

    }
    abstract class Presenter :BaseMvpPresenter<Model,View>(){
        abstract fun vip(userId:String, token:String)

    }
    interface Model{
        fun vip( userId:String, token:String):Observable<BaseResponseEntity<MemberBean>>

    }

}