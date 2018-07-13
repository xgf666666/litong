package com.weibiaogan.litong.mvp.model

import com.weibiaogan.litong.common.AppApi
import com.weibiaogan.litong.entity.ProjectPublicNoteBean
import com.weibiaogan.litong.mvp.contract.ProjectPublicNoteContract
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import io.reactivex.Observable

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
class ProjectPublicNoteModel: ProjectPublicNoteContract.Model {
    override fun note(userId: String, token: String)=AppApi.Api().note(userId,token)

}