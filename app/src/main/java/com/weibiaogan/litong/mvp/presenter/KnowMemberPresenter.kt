package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.KnowMemberCOntract
import com.weibiaogan.litong.mvp.model.KnowMemberModel
import com.weibiaogan.litong.utils.showToast

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
class KnowMemberPresenter:KnowMemberCOntract.Prsenter() {
    override fun getvipContent() {
        getModel().getvipContent(Constants.getToken().user_id.toString(),Constants.getToken().token).ui({
            getView()?.setView(it.data!!)
        },{
            getView()?.showToast(it)
        })
    }

    override fun createModel(): KnowMemberCOntract.Model =KnowMemberModel()
}