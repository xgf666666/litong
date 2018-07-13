package com.weibiaogan.litong.ui.project

import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.ProjectPublicNoteBean
import com.weibiaogan.litong.mvp.contract.ProjectPublicNoteContract
import com.weibiaogan.litong.mvp.presenter.ProjectPublicNotePresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_project_public_note.*

class ProjectPublicNoteActivity : BaseMvpActivity<ProjectPublicNotePresenter>(),ProjectPublicNoteContract.View {
    override fun getNote(projectPublicNoteBean: ProjectPublicNoteBean) {
        wb_note.loadDataWithBaseURL(null,projectPublicNoteBean.content,"text/html","utf-8",null)
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): ProjectPublicNotePresenter =ProjectPublicNotePresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int=R.layout.activity_project_public_note

    /**
     * 初始化数据状态
     */
    override fun initData() {
        getPresenter().note()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }
}
