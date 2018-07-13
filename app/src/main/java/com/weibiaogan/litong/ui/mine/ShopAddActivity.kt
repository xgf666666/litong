package com.weibiaogan.litong.ui.mine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.ShopAddContract
import com.weibiaogan.litong.mvp.presenter.ShopAddPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_shop_add.*
import android.content.Intent
import android.net.Uri
import android.R.attr.versionCode
import android.content.pm.PackageInfo




/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:商家入驻
 */

class ShopAddActivity : BaseMvpActivity<ShopAddPresenter>(),ShopAddContract.View {
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): ShopAddPresenter =ShopAddPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_shop_add

    /**
     * 初始化数据状态
     */
    override fun initData() {
        var phone=intent.getStringExtra("phone")
        tv_kefuPhone.text="客服电话:"+phone

        bt_submit.setOnClickListener{
            val uri = Uri.parse("tel:"+phone)
            val it = Intent(Intent.ACTION_DIAL, uri)
            startActivity(it)
        }
        val packageInfo = mContext.getApplicationContext()
                .getPackageManager()
                .getPackageInfo(mContext.getPackageName(), 0)
        var localVersion = packageInfo.versionCode
        tv_banben.text="v "+localVersion
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }

}
