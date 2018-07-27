package com.weibiaogan.litong.ui.blacklist

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.ui.blacklist.BlacklistContract
import com.weibiaogan.litong.ui.blacklist.BlacklistModel
import com.weibiaogan.litong.utils.loadDefulatRefresh
import com.weibiaogan.litong.utils.showToast

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
class BlacklistPresenter :  BlacklistContract.Presenter() {

    override fun getData(page: Int) {
        if (Constants.isLogin()) {


            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().getData(userId, token, "$page",Constants.getLocation()[0],Constants.getLocation()[1])
                    .loadDefulatRefresh(page == 1,getView()!!)
                    .ui({
                        getView()?.addData(it.data!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }else{
            getView()?.showToast("请先登录")
        }
    }

    override fun delBack(id: String) {
        if (Constants.isLogin()) {


            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            getModel().delBack(userId, token, "$id")
                    ?.ui({
                        getView()?.delSuccess(it?.msg!!)
                    }, {
                        getView()?.showToast(it)
                    })
        }else{
            getView()?.showToast("请先登录")
        }
    }

    override fun createModel(): BlacklistContract.Model {
        return BlacklistModel()
    }
}
