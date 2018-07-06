package com.weibiaogan.litong.ui.blacklist

import android.support.v7.widget.LinearLayoutManager
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecActivity
import kotlinx.android.synthetic.main.activity_blacklist.*

/**
 * author: Gubr
 * date: 2018/7/5
 * describe:
 */

class BlacklistActivity:BaseMvpLcecActivity<RelativeLayout,List<Any>, BlacklistContract.Model, BlacklistContract.View, BlacklistPresenter>(),BlacklistContract.View{

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_blacklist
    }

    override fun createPresenter(): BlacklistPresenter {
        return BlacklistPresenter()
    }




    override fun loadData(refresh: Boolean) {
        presenter.getData(true,0)

    }


    private val adapter by lazy {
        BuybackAdapter(null)
    }






    override fun initData() {
        super.initData()
        initrecyclerView()
        getPresenter().getData(true, 0)
    }

    override fun initEvent() {

        view_content.setOnRefreshListener { getPresenter().getData(true, 0) }

    }

    private fun initrecyclerView() {


        adapter.setOnItemClickListener { _, view, position ->
            val item = adapter.getItem(position)
        }

        adapter.setOnItemChildClickListener { _, view, position ->


        }
        adapter.setOnLoadMoreListener({ getPresenter().loadData() }, recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


    override fun setData(data: List<Any>?) {

        adapter.setNewData(data)
        showContent()
        view_content.isRefreshing = false
        if (data?.size?:0==0){
            adapter.setEmptyView(R.layout.item_view_empty,recyclerView)
        }
        if (data?.size ?: 0 >= 10) {
            adapter.setEnableLoadMore(true)
        } else {
            adapter.loadMoreEnd()
            adapter.setEnableLoadMore(false)
        }


    }

    override fun addData(data: List<Any>?) {

        if (data != null) {
            adapter.addData(data)
        }
        if (data?.size ?: 0 >= 10) {
            adapter.loadMoreComplete()
        } else {
            adapter.loadMoreEnd()
        }
    }




    class BuybackAdapter(data: MutableList<Any>?) : BaseQuickAdapter<Any, BaseViewHolder>(R.layout.item_backlist, data) {
        override fun convert(helper: BaseViewHolder?, item: Any) {

        }
    }


}