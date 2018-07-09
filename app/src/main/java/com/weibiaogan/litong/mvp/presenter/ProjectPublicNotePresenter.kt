package com.weibiaogan.litong.mvp.presenter

import com.weibiaogan.litong.mvp.contract.KnowMemberCOntract
import com.weibiaogan.litong.mvp.contract.ProjectPublicNoteContract
import com.weibiaogan.litong.mvp.model.ProjectPublicNoteModel

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
class ProjectPublicNotePresenter:ProjectPublicNoteContract.Prsenter() {
    override fun createModel(): ProjectPublicNoteContract.Model =ProjectPublicNoteModel()
}