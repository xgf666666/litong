package com.weibiaogan.litong.ui.project

import android.view.View
import com.weibiaogan.litong.R
import com.weibiaogan.litong.extensions.getTString
import com.weibiaogan.litong.extensions.setOnPerCheckLoginClickListner
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.ProjectContract
import com.weibiaogan.litong.mvp.presenter.ProjectPresenter
import com.xx.baseuilibrary.mvp.BaseMvpFragment
import com.weibiaogan.litong.ui.location.GeoToScreenActivity
import kotlinx.android.synthetic.main.fragment_project.*


/**
 * author: Gubr
 * date: 2018/5/7
 * describe:
 */
class Projectragment : BaseMvpFragment<ProjectContract.Model, ProjectContract.View, ProjectPresenter>(), ProjectContract.View, View.OnClickListener {



    override fun createPresenter(): ProjectPresenter {
        return ProjectPresenter()
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView(view: View?) {
    }

    override fun initEvent(view: View?) {
        bt_submit.setOnPerCheckLoginClickListner {
        pullProject()
        }
        iv_project_location.setOnClickListener {
            startActivity(GeoToScreenActivity::class.java)
        }
    }

    override fun successful() {
        toast("发布成功")
    }




    private fun pullProject(){
        val projectName = et_project_name.getTString()
        val projectEndtime = et_project_endTime.getTString()
        val projectLocation = et_project_location.getTString()
        val projectCost = et_project_cost.getTString()
        val projectFirstRatio = et_project_first_ratio.getTString()
        val projectSecondRatio = et_project_second_ratio.getTString()
        val projectIntroduction = et_project_introduction.getTString()
        val projectAddress=""
        val projectAreaId=""
        val projectLastRatio=""
        val projectLanlog=""
        getPresenter().pullProject(projectName,projectIntroduction
                ,projectEndtime,projectAddress,projectAreaId
                ,projectFirstRatio,projectSecondRatio
                ,projectLastRatio,"",projectLanlog,projectCost)
    }

    override fun initData() {
    }

    override fun onClick(v: View) {
    }
}