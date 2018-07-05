package com.weibiaogan.litong.ui.mine

import android.view.View
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.ChangeNicknameContract
import com.weibiaogan.litong.mvp.presenter.ChangeNicknamePresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_changenickname.*

/**
 * author: Gubr
 * date: 2018/5/8
 * describe:
 */
class ChangeNicknameActivity : BaseMvpActivity<ChangeNicknamePresenter>(), ChangeNicknameContract.View, View.OnClickListener {


    override fun getName(): String {
        return  et_name.text.toString().trim()
    }


    override fun createPresenter(): ChangeNicknamePresenter {
        return ChangeNicknamePresenter()
    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_changenickname
    }

    override fun initData() {
        val name = intent.getStringExtra("name")

        et_name.setText(name)


    }

    override fun initEvent() {
        iv_clean.setOnClickListener(this)
        bt_submit.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_clean-> {
                et_name.setText("")
            }
            R.id.bt_submit -> {

                getPresenter().changeUserInfo()
            }
        }
    }

    override fun successful() {
        finish()
    }
}









