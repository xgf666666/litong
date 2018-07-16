package com.weibiaogan.litong.mvp.contract

import com.weibiaogan.litong.entity.MemberBean
import com.weibiaogan.litong.entity.PayBean
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
        fun payResult(payBean: PayBean)

    }
    abstract class Presenter :BaseMvpPresenter<Model,View>(){
        abstract fun vip(userId:String, token:String)
        abstract fun pay(ptId:String,payType:String ,ptType:String)

    }
    interface Model{
        fun vip( userId:String, token:String):Observable<BaseResponseEntity<MemberBean>>
        fun pay(userId:String, token:String,ptId:String,payType:String ,ptType:String):Observable<BaseResponseEntity<PayBean>>

    }

}