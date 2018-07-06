package com.weibiaogan.litong.ui.search

import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.SearchProjectContract
import com.weibiaogan.litong.mvp.presenter.SearchProjectPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  项目搜索 页面
 */
class SearchProjectActivity : BaseMvpActivity<SearchProjectContract.Presenter>(){

    override fun createPresenter(): SearchProjectContract.Presenter = SearchProjectPresenter()

    override fun getActivityLayoutId(): Int = R.layout.activity_search_project

    override fun initData() {

    }

    override fun initEvent() {

    }
}