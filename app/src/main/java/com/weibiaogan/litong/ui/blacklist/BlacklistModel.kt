package com.weibiaogan.litong.ui.blacklist

import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
class BlacklistModel : BlacklistContract.Model {
    override fun getData(): Observable<BaseResponseEntity<List<Any>>> {
        //
        return Observable.just(BaseResponseEntity<List<Any>>(1))
    }

}
