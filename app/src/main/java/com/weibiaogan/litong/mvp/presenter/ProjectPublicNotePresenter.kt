package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.ui
import com.weibiaogan.litong.mvp.contract.KnowMemberCOntract
import com.weibiaogan.litong.mvp.contract.ProjectPublicNoteContract
import com.weibiaogan.litong.mvp.model.ProjectPublicNoteModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
class ProjectPublicNotePresenter:ProjectPublicNoteContract.Prsenter() {
    override fun note() {
        val userId = Constants.getToken().user_id.toString()
        val token = Constants.getToken().token
        getModel().note(userId,token).ui({
                getView()?.getNote(it.data!!)
        },{
            getView()?.showToast(it)
        })

    }

    override fun createModel(): ProjectPublicNoteContract.Model =ProjectPublicNoteModel()
}