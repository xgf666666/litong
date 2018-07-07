package com.weibiaogan.litong.ui.mine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.extensions.startActivity
import kotlinx.android.synthetic.main.activity_bos_identy.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:工人认证1
 */


class WorkerIDentyOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_identy_one)
        bt_submit.setOnClickListener{startActivity(WorkerIDentyTwoActivity::class.java)}
    }
}
