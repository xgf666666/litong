package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.SearchProjectBean
import com.weibiaogan.litong.mvp.contract.SearchProjectContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:
 */
class SearchProjectModel : SearchProjectContract.Model {
    override fun searchProject(pt_name: String, page: String, lat : String , lng : String): Observable<BaseResponseEntity<List<SearchProjectBean>>> {
        return AppApi.Api().searchProject(pt_name,page,lat, lng)
    }

}