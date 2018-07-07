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
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.home.HomeAdapter
import com.weibiaogan.litong.entity.HomeBean
import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.weibiaogan.litong.mvp.presenter.HomePresenter
import com.weibiaogan.litong.ui.search.SearchProjectActivity
import com.weibiaogan.litong.ui.store.StoreListActivity
import com.weibiaogan.litong.ui.work.WorkListActivity
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * author: Gubr
 * date: 2018/5/6
 * describe:
 */
class HomeFragment : BaseMvpLcecFragment<LinearLayout, Any,HomeConstract.Model, HomeConstract.View, HomePresenter>(), HomeConstract.View, View.OnClickListener {
    override fun getHomeData(bean: HomeBean?) {
        rv_home_bottom.adapter = HomeAdapter(getListMulti(), bean)
    }

    val banner_imgs : List<Int> = arrayListOf(R.mipmap.img_banner,R.mipmap.img_banner,R.mipmap.img_banner)
    var headView : View? = null

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
        //tv_home_location.setOnClickListener(this)
        ll_home_search.setOnClickListener(this)
    }

    override fun initData() {
        showContent()
        headView = LayoutInflater.from(mContext).inflate(R.layout.home_head_view, null, false)
        rv_home_bottom.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        var bean = HomeBean()
        //var adList = arrayListOf<HomeBean.AdveBean>()
        var workList = arrayListOf<HomeBean.WorkerBean>()
        var pList = arrayListOf<HomeBean.ProjectBean>()
        var sList = arrayListOf<HomeBean.StoreBean>()
        for (i in 0..10){
            //var adve = HomeBean.AdveBean()
            var project = HomeBean.ProjectBean()
            project.distance = 700
            project.end_time = "2018.02.02"
            project.all_price = "500"
            project.pt_name = "名字"
            pList.add(project)
        }
        for (i in 0..2){
            var work = HomeBean.WorkerBean()
            work.distance = 700
            work.user_phone = "13000000000"
            work.user_img = ""
            workList.add(work)
        }
        for (i in 0..3){
            var store = HomeBean.StoreBean()
            store.distance = 700
            store.st_type = "服务类型"
            store.st_name = "名字"
            store.st_img = ""
            sList.add(store)
        }
        bean.worker = workList
        bean.project = pList
        bean.store = sList
        var adapter = HomeAdapter(getListMulti(), bean)

        headView?.findViewById<ConvenientBanner<Int>>(R.id.cb_home_top)?.setPages(CBViewHolderCreator { ImageHolderView() } , banner_imgs)?.setPointViewVisible(true)?.startTurning(2000)

        adapter.addHeaderView(headView)

        rv_home_bottom.adapter = adapter
        //presenter.getHomeData(1,)
    }

    fun getListMulti() : List<HomeAdapter.HomeMultiItem>{
        var multiList = arrayListOf<HomeAdapter.HomeMultiItem>()
        multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_ONE))
        multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_TWO))
        multiList.add(HomeAdapter.HomeMultiItem(HomeAdapter.HomeMultiItem.ITEM_TYPE_THREE))
        return multiList
    }

    override fun setData(data: Any?) {
        showContent()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_home_project -> Unit
            R.id.tv_home_work -> startActivity(Intent(mContext,WorkListActivity::class.java))
            R.id.tv_home_material -> startActivity(Intent(mContext,StoreListActivity::class.java))
            R.id.tv_home_history -> Unit
            R.id.ll_home_search -> startActivity(Intent(mContext,SearchProjectActivity::class.java))
        }
    }

    inner class ImageHolderView : Holder<Int>{
        var imageview : ImageView? = null

        override fun UpdateUI(context: Context?, position: Int, data: Int?) {
            imageview?.setImageResource(data ?: R.mipmap.img_banner)
        }

        override fun createView(context: Context?): View {
            imageview = ImageView(this@HomeFragment.context)
            imageview?.scaleType = ImageView.ScaleType.FIT_XY
            return imageview as View
        }

    }


}