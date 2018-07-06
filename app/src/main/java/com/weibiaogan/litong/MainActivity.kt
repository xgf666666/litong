package com.weibiaogan.litong

import android.content.Intent
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import com.blankj.utilcode.util.ActivityUtils
import com.weibiaogan.litong.common.Constants.KEY_INTENT_MAIN
import com.weibiaogan.litong.ui.orders.OrdersFragment
import com.weibiaogan.litong.ui.home.HomeFragment
import com.weibiaogan.litong.ui.mine.MineFragment
import com.weibiaogan.litong.ui.project.Projectragment
import com.xx.baseuilibrary.mvp.BaseMvpViewActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : BaseMvpViewActivity() {

    companion object {
        const val INDEX_HOME = 0
        const val INDEX_PERSON = 3//个人中心
    }


    private var fragments: ArrayList<Fragment?>? = null

    override fun getActivityLayoutId(): Int = R.layout.activity_main

    override fun initEvent() {
        radio.setOnCheckedChangeListener { _, checkedId ->
          /*  ll_search_area.visibility= if (checkedId==0) View.VISIBLE else View.GONE*/
//            iv_setup.visibility=if (checkedId==3)View.VISIBLE else View.GONE

            tb_cart_setup.visibility=if (checkedId==2) View.VISIBLE else View.GONE
            showFragment(checkedId)
            changeTitle(checkedId)
            supportFragmentManager.popBackStack()
        }


       /* ll_search_area.setOnClickListener {

           startActivity(SearchActivity::class.java)
        }*/

        iv_setup.setOnClickListener {

        }

    }

    private fun changeTitle(i: Int) {
        when (i) {
            0 -> tv_title.text="首页"
            1 -> tv_title.text="发布项目"
            2 -> tv_title.text="我要接单"
            3 -> tv_title.text="我的"
        }
    }


    /**
     * 显示Fragment
     */
    private fun showFragment(checkedId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in fragments!!.indices) {
            val fragment = fragments!![i]
            if (i == checkedId) {
                //先检查Fragment是否创建
                if (fragment == null) {
                    when (i) {
                        0 -> {
                            fragments!![i] = HomeFragment()

                        }
                        1 -> fragments!![i] = Projectragment()
                        2 -> fragments!![i] = OrdersFragment()
                        3 -> fragments!![i] = MineFragment()
                    }
                    //添加到管理类
                    fragmentTransaction.add(R.id.container, fragments!![i])
                }
                fragmentTransaction.show(fragments!![i])
            } else {
                if (fragment != null) {
                    fragmentTransaction.hide(fragment)
                }
            }
        }
        fragmentTransaction.commit()
    }

    /**
     *初始化Fragments
     */
    private fun initFragments() {
        if (fragments == null) {
            //初始化只生成首页碎片
            fragments = arrayListOf(HomeFragment(), null, null, null)
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragments!![0])
                .show(fragments!![0])
                .commit()

    }

    var isExit: Boolean? = false//是否退出登录
    /**
     * 双击返回桌面
     */
    private fun back2exit() {
        if (isExit!!) {
            ActivityUtils.finishAllActivities(true)
        }
        isExit = true
        showToast("再按一次回到桌面")
        Timer().schedule(object : TimerTask() {
            override fun run() {
                isExit = false
            }

        }, 1500)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back2exit()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun initData() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = ContextCompat.getColor(mContext, R.color.colorWhite)
//        }

        //重置RadioGroup子项id
        for (i in 0 until radio.childCount) {
            radio!!.getChildAt(i)!!.id = i
        }
        initFragments()
        radio.check(INDEX_HOME)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val intExtra = intent!!.getIntExtra(KEY_INTENT_MAIN, INDEX_HOME)
        (radio.getChildAt(intExtra) as RadioButton).isChecked = true
    }

}
