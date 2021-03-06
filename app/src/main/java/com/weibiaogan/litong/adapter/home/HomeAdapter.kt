package com.weibiaogan.litong.adapter.home

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import com.weibiaogan.litong.ui.store.StoreDetailActivity
import com.weibiaogan.litong.ui.work.WorkDetailActivity

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
class HomeAdapter(datas : List<HomeMultiItem>) : BaseMultiItemQuickAdapter<HomeAdapter.HomeMultiItem,BaseViewHolder>(datas){

    var threeAdapter : ItemThreeAdapter? = null
    init {
        addItemType(HomeMultiItem.ITEM_TYPE_ONE, R.layout.home_rv_item_one)
        addItemType(HomeMultiItem.ITEM_TYPE_TWO, R.layout.home_rv_item_one)
        addItemType(HomeMultiItem.ITEM_TYPE_THREE, R.layout.home_rv_item_one)
    }
    override fun convert(helper: BaseViewHolder?, item: HomeMultiItem?) {
        when(helper?.itemViewType){
            HomeMultiItem.ITEM_TYPE_ONE -> setItemOneView(helper,item?.bean)
            HomeMultiItem.ITEM_TYPE_TWO -> setItemTwoView(helper,item?.bean)
            HomeMultiItem.ITEM_TYPE_THREE -> setItemThreeView(helper,item?.bean)
        }
    }

    fun setItemOneView(helper: BaseViewHolder?,bean : HomeBean?){
        if (bean?.worker == null){
            return
        }
        helper?.setText(R.id.home_rv_item_txt,R.string.home_rv_work)
        var view = helper?.getView<RecyclerView>(R.id.home_rv_item_rv)
        view?.layoutManager = GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false)
        var adapter = ItemOneAdapter(bean?.worker)
        adapter.setOnItemChildClickListener { adapter, view, position -> WorkDetailActivity.startWorkDetail(mContext, (adapter as ItemOneAdapter).data[position].user_id) }
        view?.adapter = adapter
        view?.isNestedScrollingEnabled = false
    }

    fun setItemTwoView(helper: BaseViewHolder?,bean : HomeBean?){
        if (bean?.store == null){
            return
        }
        helper?.setText(R.id.home_rv_item_txt,R.string.home_rv_material)
        var view = helper?.getView<RecyclerView>(R.id.home_rv_item_rv)
        view?.layoutManager = GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false)
        var adapter = ItemTwoAdapter(bean?.store)
        adapter.setOnItemClickListener { adapter, view, position -> StoreDetailActivity.startStoreDetail(mContext,(adapter as ItemTwoAdapter).data[position].st_id) }
        view?.adapter = adapter
        view?.isNestedScrollingEnabled = false
    }

    fun setItemThreeView(helper: BaseViewHolder?,bean : HomeBean?){
        if (bean?.project == null){
            return
        }
        var view = helper?.getView<RecyclerView>(R.id.home_rv_item_rv)
        helper?.setText(R.id.home_rv_item_txt,R.string.home_rv_project)
        view?.layoutManager = GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false)
        threeAdapter = ItemThreeAdapter(bean?.project)
        threeAdapter?.setOnItemClickListener { adapter, view, position -> OrdersDetailActivity.startProjectDetail(mContext,(adapter as ItemThreeAdapter).data[position].pt_id.toString()) }
        view?.adapter = threeAdapter
        view?.isNestedScrollingEnabled = false
    }

    fun addHomeData(list : List<HomeBean.ProjectBean>){
        if (threeAdapter != null){
            threeAdapter?.addData(list)
        }
    }

    class HomeMultiItem(val type : Int,val bean : HomeBean) : MultiItemEntity{

        companion object {
            val ITEM_TYPE_ONE = 0x01
            val ITEM_TYPE_TWO = 0x02
            val ITEM_TYPE_THREE = 0x03
        }

        override fun getItemType(): Int = type
    }

}