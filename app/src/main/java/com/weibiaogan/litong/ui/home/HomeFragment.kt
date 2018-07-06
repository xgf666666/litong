package com.weibiaogan.litong.ui.home

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.HomeConstract
import com.weibiaogan.litong.mvp.presenter.HomePresenter
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * author: Gubr
 * date: 2018/5/6
 * describe:
 */
class HomeFragment : BaseMvpLcecFragment<SwipeRefreshLayout, Any,HomeConstract.Model, HomeConstract.View, HomePresenter>(), HomeConstract.View, View.OnClickListener {

    val banner_imgs : List<Int> = arrayListOf(R.mipmap.img_banner,R.mipmap.img_banner,R.mipmap.img_banner)

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
        //home_convenien_banner?.setPages(CBViewHolderCreator { ImageHolderView() }, banner_imgs)
    }

    override fun setData(data: Any?) {
    }




    override fun onClick(v: View?) {
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