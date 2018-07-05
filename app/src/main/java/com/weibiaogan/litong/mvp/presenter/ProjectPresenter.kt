package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.ProjectContract
import com.weibiaogan.litong.mvp.model.ProjectModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:发现
 */
class ProjectPresenter : ProjectContract.Presenter() {
    override fun pullProject(ptname: String, ptdescribe: String,
                             endtime: String, ptaddress: String, areaid: String, firstprice: String,
                             secondprice: String, threeprice: String, ptimgs: String, latlong: String,
                             allprice: String) {
        if (Constants.isLogin()) {
            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            if (ptname.isNullOrEmpty()) {
                getView()?.showToast("项目名不能为空")
                return
            }
            if (ptdescribe.isNullOrEmpty()) {
                getView()?.showToast("项目描述不能为空")
                return
            }
            if (endtime.isNullOrEmpty()) {
                getView()?.showToast("项目结束时间不能为空")
                return
            }
            if (ptaddress.isNullOrEmpty()) {
                getView()?.showToast("项目地址不能为空")
                return
            }
            if (areaid.isNullOrEmpty()) {
                getView()?.showToast("项目地址id不能为空")
                return
            }
            if (firstprice.isNullOrEmpty()) {
                getView()?.showToast("项目首付比例不能为空")
                return
            }
            if (secondprice.isNullOrEmpty()) {
                getView()?.showToast("项目二期比例不能为空")
                return
            }
            if (!(firstprice.toInt()+secondprice.toInt()+threeprice.toInt()==100)) {
                getView()?.showToast("付款比例加起来要100%")
            }

            if (allprice.isNullOrEmpty()) {
                getView()?.showToast("项目金额不能为空")
                return
            }





            getModel().pullProject(userId, token, ptname, ptdescribe, endtime, ptaddress, areaid, firstprice, secondprice, threeprice, ptimgs, latlong, allprice)
                    .loadDefulat(getView()!!)
                    .ui({
                        getView()?.successful()
                    }, {
                        getView()?.showToast(it)
                    })
        } else {

        }
    }

    override fun createModel(): ProjectContract.Model {
        return ProjectModel()
    }
}
