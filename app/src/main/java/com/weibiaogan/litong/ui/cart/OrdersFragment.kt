package com.weibiaogan.litong.ui.cart

import android.view.View
import android.widget.RelativeLayout
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.OrdersContract
import com.weibiaogan.litong.mvp.presenter.OrdersPresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment


/**
 * author: Gubr
 * date: 2018/5/7
 * describe:我要接单
 */
class OrdersFragment : BaseMvpLcecFragment<RelativeLayout, List<Any>, OrdersContract.Model, OrdersContract.View, OrdersPresenter>(), OrdersContract.View {



    override fun getFragmentLayoutId(): Int = R.layout.fragment_orders


    override fun loadData(refresh: Boolean) {

    }

    override fun createPresenter(): OrdersPresenter {
        return OrdersPresenter()
    }

    override fun initEvent(view: View?) {
    }

    override fun initData() {
    }

    override fun setData(data: List<Any>) {
    }





}