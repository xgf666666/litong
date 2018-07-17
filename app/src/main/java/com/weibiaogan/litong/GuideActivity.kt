package com.weibiaogan.litong

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.extensions.startActivity
import com.weibiaogan.litong.ui.login.LoginActivity
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_guide.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/17
 * describe:
 */
class GuideActivity : BaseActivity() {
    var imgs = arrayListOf(R.mipmap.img_guide_a,R.mipmap.img_guide_b,R.mipmap.img_guide_c)
    var imgList = arrayListOf<ImageView>()
    var mPointView = arrayListOf<ImageView>()
    var mPosition = 0

    override fun getActivityLayoutId(): Int = R.layout.activity_guide

    override fun initData() {
        for (i in 0 until imgs.size){
            var view = ImageView(this)
            view.scaleType = ImageView.ScaleType.CENTER_INSIDE
            view.setImageResource(imgs[i])
            imgList.add(view)
        }

        vp_guide.adapter = MyPagerAdapter(imgList)
        mPointView.add(iv_point_one)
        mPointView.add(iv_point_two)
        mPointView.add(iv_point_three)
    }

    override fun initEvent() {
        vp_guide.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mPosition = position
                if (position == imgList.size - 1){
                    btn_steup.text = "开启"
                }else{
                    btn_steup.text = "下一步"
                }
                showPoint(mPosition)
            }

        })

        btn_steup.setOnClickListener {
            if (mPosition == 2){
                //Constants.setNotFirst()
                if (Constants.isLogin()){
                    startActivity(MainActivity::class.java)
                }else{
                    startActivity(LoginActivity::class.java)
                }
                finish()
                overridePendingTransition(R.anim.scale_in,R.anim.scale_out)
            }else{
                vp_guide.currentItem = ++mPosition
                showPoint(mPosition)
            }
        }
    }

    fun showPoint(i : Int){
        for (j in 0 until mPointView.size){
            if (i == j){
                mPointView[j].setImageResource(R.mipmap.ic_point_normal)
            }else{
                mPointView[j].setImageResource(R.mipmap.ic_point_pressed)
            }
        }
    }

    class MyPagerAdapter(val datas : List<ImageView>) : PagerAdapter(){
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int = datas.size

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(datas[position])
            return datas[position]
        }

    }
}