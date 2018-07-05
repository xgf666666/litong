package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.mvp.contract.ProjectContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:发布项目
 */
class ProjectModel : ProjectContract.Model {



  override  fun pullProject(userid: String, token: String, ptname: String, ptdescribe: String, endtime: String, ptaddress: String
                            , areaid: String, firstprice: String, secondprice: String, threeprice: String, ptimgs: String, latlong: String, allprice: String):    Observable<BaseResponseEntity<List<Any>>> {
        return AppApi.Api().addProject(userid, token, ptname, ptdescribe, endtime, ptaddress, areaid, firstprice, secondprice, threeprice,
                ptimgs, latlong, allprice)
              /*  .flatMap {
                    Observable.just(BaseResponseEntity(1, listOf(Any())))
                }*/

    }
}
