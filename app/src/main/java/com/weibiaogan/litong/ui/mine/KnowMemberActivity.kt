package com.weibiaogan.litong.ui.mine

import android.app.Activity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.MemberpowrBean
import com.weibiaogan.litong.mvp.contract.KnowMemberCOntract
import com.weibiaogan.litong.mvp.presenter.KnowMemberPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_konw_member.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/5
 * describe:了解特权会员
 */
class KnowMemberActivity : BaseMvpActivity<KnowMemberPresenter>(),KnowMemberCOntract.View {
    override fun setView(memberpowrBean: MemberpowrBean) {
        wb_km.loadDataWithBaseURL(null,memberpowrBean.content,"text/html","utf-8",null)
    }

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): KnowMemberPresenter=KnowMemberPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int= R.layout.activity_konw_member

    /**
     * 初始化数据状态
     */
    override fun initData() {
        getPresenter().getvipContent()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }


}
