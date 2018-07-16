package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.entity.IsPublic
import com.weibiaogan.litong.entity.PublicProjectsBean
import com.weibiaogan.litong.mvp.contract.ProjectContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:发布项目
 */
class ProjectModel : ProjectContract.Model {
    override fun isPublic(userId: String, token: String)=AppApi.Api().isPublic(userId,token)

    override fun imgup(imagBase64: String): Observable<BaseResponseEntity<ImageBean>> =AppApi.Api().imgup(imagBase64)

    override fun getWorkerTyle(userId: String, token: String)= AppApi.Api().publicWorkerTyle(userId,token)

    override  fun pullProject(userid: String, token: String, ptname: String, ptdescribe: String, endtime: String, ptaddress: String
                            , areaid: String, firstprice: String, secondprice: String, threeprice: String, ptimgs: String, latlong: String, allprice: String):    Observable<BaseResponseEntity<PublicProjectsBean>> {
        return AppApi.Api().addProject(userid, token, ptname, ptdescribe, endtime, ptaddress, areaid, firstprice, secondprice, threeprice,
                ptimgs, latlong, allprice)


    }
}
