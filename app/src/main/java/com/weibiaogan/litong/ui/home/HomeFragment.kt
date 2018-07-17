package com.weibiaogan.litong.ui.home

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.home.HomeAdapter
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.ui.project.ProjectListActivity
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.weibiaogan.litong.mvp.presenter.HomePresenter
import com.weibiaogan.litong.ui.project.HistoryProjectActivity
import com.weibiaogan.litong.ui.store.StoreListActivity
import com.weibiaogan.litong.ui.work.WorkListActivity
import com.weibiaogan.litong.utils.addData
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * author: Gubr
 * date: 2018/5/6
 * describe:
 */
class HomeFragment : BaseMvpLcecFragment<LinearLayout, Any,HomeConstract.Model, HomeConstract.View, HomePresenter>(), HomeConstract.View, View.OnClickListener, OnRefreshLoadMoreListener {
    //val banner_imgs : List<Int> = arrayListOf(R.mipmap.img_banner,R.mipmap.img_banner,R.mipmap.img_banner)
    val banner_imgs = arrayListOf<String>()

    var headView : View? = null
    var adapter = HomeAdapter(getListMulti(HomeBean()))
    var mCurrentPage = 1

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun loadData(refresh: Boolean) {

    }

    override fun createPresenter(): HomePresenter {
        return HomePresenter()
    }

    override fun initEvent(view: View?) {
        headView?.findViewById<TextView>(R.id.tv_home_work)?.setOnClickListener(this)
        headView?.findViewById<TextView>(R.id.tv_home_material)?.setOnClickListener(this)
        headView?.findViewById<TextView>(R.id.tv_home_project)?.setOnClickListener(this)
        headView?.findViewById<TextView>(R.id.tv_home_history)?.setOnClickListener(this)
        refresh_home.setOnRefreshLoadMoreListener(this)
    }

    override fun initData() {
        //showContent()
        showLoading()
        headView = LayoutInflater.from(mContext).inflate(R.layout.home_head_view, null, false)
        rv_home_bottom.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)

        adapter.addHeaderView(headView)

        rv_home_bottom.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        mCurrentPage = 1
        presenter.getHomeData(mCurrentPage.toString())
    }

    fun getListMulti(bean : HomeBean) : List<HomeAdapter.HomeMultiItem>{
        var multiList = arrayListOf<HomeAdapter.HomeMultiItem>()
        if (mCurrentPage != 1){
            multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_THREE,bean))
            return multiList
        }
        multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_ONE,bean))
        multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_TWO,bean))
        multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_THREE,bean))
        return multiList
    }

    override fun setData(data: Any?) {
        showContent()
        //adapter.setNewData(getListMulti())
        if ((data as HomeBean).adve != null){
            for (bean in (data).adve){
                banner_imgs.add(bean.ad_img)
            }
            headView?.findViewById<ConvenientBanner<String>>(R.id.cb_home_top)?.
                    setPages( { ImageHolderView() } , banner_imgs)?.
                    setPointViewVisible(true)?.startTurning(2000)
        }
        refresh_home.addData(adapter,getListMulti(data))
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_home_project -> startActivity(Intent(mContext, ProjectListActivity::class.java))
            R.id.tv_home_work -> startActivity(Intent(mContext,WorkListActivity::class.java))
            R.id.tv_home_material -> startActivity(Intent(mContext,StoreListActivity::class.java))
            R.id.tv_home_history -> startActivity(Intent(mContext,HistoryProjectActivity::class.java))
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        presenter.getHomeData(mCurrentPage.toString())
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        presenter.getHomeData((++mCurrentPage).toString())
    }

    inner class ImageHolderView : Holder<String>{
        var imageview : ImageView? = null

        override fun UpdateUI(context: Context?, position: Int, data: String?) {
            imageview?.loadImag(data!!,plach = R.mipmap.img_banner,error = R.mipmap.img_banner)
        }

        override fun createView(context: Context?): View {
            imageview = ImageView(this@HomeFragment.context)
            imageview?.scaleType = ImageView.ScaleType.FIT_XY
            return imageview as View
        }

    }


}