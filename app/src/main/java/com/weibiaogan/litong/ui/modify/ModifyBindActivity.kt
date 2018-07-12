package com.weibiaogan.litong.ui.modify

import android.support.v4.app.Fragment
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.mvp.contract.ModifyBindContract
import com.weibiaogan.litong.mvp.presenter.ModifyBindPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_change_pw2.*
import java.util.*

/**
 * author: Gubr
 * date: 2018/5/23
 * describe:
 */
class ModifyBindActivity:BaseMvpActivity<ModifyBindPresenter>(),ModifyBindContract.View{

    private var fragments: ArrayList<Fragment?>? = null

    override fun createPresenter(): ModifyBindPresenter {
        return ModifyBindPresenter()

    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_modifybindarea
    }

    override fun initData() {

        initFragments()
        showFragment(0)
    }

    override fun initEvent() {
    }

    /**
     * 显示Fragment
     */
    public fun showFragment(checkedId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in fragments!!.indices) {
            val fragment = fragments!![i]
            if (i == checkedId) {
                //先检查Fragment是否创建
                if (fragment == null) {
                    when (i) {
                        0 -> {
                            fragments!![i] = ModifyBindFragment1()

                        }
                        1 -> fragments!![i] = ModifyBindFragment2()
//                        2 ->  fragments!![i] = ModifyBindFragment3()
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
            //初始化只生成碎片
            fragments = arrayListOf(ModifyBindFragment1(), null, null)
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragments!![0])
                .show(fragments!![0])
                .commit()

    }

}