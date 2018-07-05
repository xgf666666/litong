package com.weibiaogan.litong.ui.home

import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.weibiaogan.litong.mvp.presenter.HomePresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment


/**
 * author: Gubr
 * date: 2018/5/6
 * describe:
 */
class HomeFragment : BaseMvpLcecFragment<SwipeRefreshLayout, Any,HomeConstract.Model, HomeConstract.View, HomePresenter>(), HomeConstract.View, View.OnClickListener {

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun loadData(refresh: Boolean) {

    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun initEvent(view: View?) {
    }

    override fun initData() {
    }

    override fun setData(data: Any?) {
    }




    override fun onClick(v: View?) {
    }


}