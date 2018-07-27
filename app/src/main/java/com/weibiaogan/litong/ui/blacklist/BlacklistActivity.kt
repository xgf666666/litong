package com.weibiaogan.litong.ui.blacklist

import android.support.v7.widget.LinearLayoutManager
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.BlackBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.utils.addData
import com.weibiaogan.litong.utils.changeKm
import com.weibiaogan.litong.utils.initSmartRefresh
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_blacklist.*

/**
 * author: Gubr
 * date: 2018/7/5
 * describe:
 */

class BlacklistActivity : BaseMvpActivity<BlacklistContract.Presenter>(),BlacklistContract.View, OnRefreshLoadMoreListener {

    var mCurrentPage = 1
    var mDelPosition = -1

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_blacklist
    }

    override fun createPresenter(): BlacklistPresenter {
        return BlacklistPresenter()
    }

    private val adapter by lazy {
        BuybackAdapter(arrayListOf())
    }

    override fun initData() {
        initrecyclerView()
        getPresenter().getData(mCurrentPage)

        refresh_black_list.initSmartRefresh()
    }

    override fun initEvent() {
        refresh_black_list.setOnRefreshLoadMoreListener(this)
        //view_content.setOnRefreshListener { getPresenter().getData(true, 0) }
    }

    private fun initrecyclerView() {

        adapter.setOnItemClickListener { adapter, view, position ->

        }

        adapter.setOnItemChildClickListener { adapter, view, position ->
            //移除黑名单
            mDelPosition = position
            getPresenter().delBack((adapter as BuybackAdapter).data[position].id.toString())
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun addData(data: List<BlackBean>) {
        refresh_black_list.addData(adapter,data)
    }

    override fun delSuccess(msg: String) {
        showToast(msg)
        adapter.data.removeAt(mDelPosition)
        adapter.notifyItemChanged(mDelPosition)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().getData(++mCurrentPage)
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().getData(mCurrentPage)
    }

    class BuybackAdapter(data: List<BlackBean>) : BaseQuickAdapter<BlackBean, BaseViewHolder>(R.layout.item_backlist, data) {
        override fun convert(helper: BaseViewHolder?, item: BlackBean) {
            helper?.setText(R.id.tv_name,item.nickname)
                    ?.setText(R.id.tv_phone,item.user_phone)
                    ?.setText(R.id.tv_distance,item.distance.changeKm())
                    ?.setText(R.id.tv_service_type,"服务类型："+item.worker_service)
                    ?.setText(R.id.tv_location,item.worker_address)
                    ?.addOnClickListener(R.id.tv_remove_blacklist)


            helper?.getView<CircleImageView>(R.id.iv_avatar)?.loadImag(item.user_img,plach = R.mipmap.img_face)

        }
    }

}