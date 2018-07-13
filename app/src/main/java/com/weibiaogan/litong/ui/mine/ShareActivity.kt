package com.weibiaogan.litong.ui.mine

import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.ShareUserBean
import com.weibiaogan.litong.mvp.contract.ShareContract
import com.weibiaogan.litong.mvp.presenter.SharePresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager
import kotlinx.android.synthetic.main.activity_share.*

class ShareActivity : BaseMvpActivity<SharePresenter>(),ShareContract.View {
    override fun succeful(shareUserBean: ShareUserBean) {
        Glide.with(this).load(Retrofit2Manager.instance.apiConfigProvider?.debugHost+shareUserBean?.user?.user_img?: "")
                .fallback(R.drawable.personal_center_)
                .placeholder(R.drawable.personal_center_)
                .error(R.drawable.personal_center_)
                .into(iv_head)
//        Log.i("dfaggae",Retrofit2Manager.instance.apiConfigProvider?.debugHost+shareUserBean?.share?.share_img)
        Glide.with(this).load(shareUserBean?.share?.share_img?: "")
                .into(iv_weweima)
        tv_name.setText(shareUserBean.user.nickname)
        dismissLoadingDialog()

    }


    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter()= SharePresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_share

    /**
     * 初始化数据状态
     */
    override fun initData() {
        showLoadingDialog()
        getPresenter().share()
        ib_back.setOnClickListener{
            finish()
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
    }
}
