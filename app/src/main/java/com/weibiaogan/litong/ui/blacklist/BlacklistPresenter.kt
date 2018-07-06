package com.weibiaogan.litong.ui.blacklist

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.loadDefulat
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.ui.blacklist.BlacklistContract
import com.weibiaogan.litong.ui.blacklist.BlacklistModel

/**
 * author: Gubr
 * date: 2018/5/5
 * describe:黑名单列表
 */
class BlacklistPresenter :  BlacklistContract.Presenter() {




    private var page = 0

    override fun getData(isRefresh: Boolean, page: Int) {
        if (Constants.isLogin()) {


            val userId = Constants.getToken().user_id.toString()
            val token = Constants.getToken().token
            this.page = page
            getModel().getData(userId, token, "$page")
                    .apply {
                        if (isRefresh) loadDefulat(getView()!!)
                    }
                    .ui({
                        var data = it.data
                        if (isRefresh) {
                            getView()?.setData(data)
                        } else {
                            getView()?.addData(data)
                        }
                    }, {
                        getView()?.showToast(it)
                    })
        }else{
            getView()?.showToast("请先登录")
        }
    }


    override fun loadData() {
        getData(false, ++page)
    }



    override fun createModel(): BlacklistContract.Model {
        return BlacklistModel()
    }
}
