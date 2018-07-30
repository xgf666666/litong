package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.umeng.socialize.UMShareAPI
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.ShareUserBean
import com.weibiaogan.litong.mvp.contract.ShareContract
import com.weibiaogan.litong.mvp.presenter.SharePresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager
import kotlinx.android.synthetic.main.activity_share.*
import android.support.v4.app.ActivityCompat
import android.os.Build
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.DiskCache
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.shareboard.SnsPlatform


class ShareActivity : BaseMvpActivity<SharePresenter>(),ShareContract.View {

    var shareUserBeans:ShareUserBean?=null
    override fun succeful(shareUserBean: ShareUserBean) {
        shareUserBeans=shareUserBean
        Glide.with(this).load(Retrofit2Manager.instance.apiConfigProvider?.debugHost+shareUserBean?.user?.user_img?: "")
                .fallback(R.drawable.personal_center_)
                .placeholder(R.drawable.personal_center_)
                .error(R.drawable.personal_center_)
                .into(iv_head)
        Glide.with(this).load(shareUserBean?.share_qrcode?: "")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
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
        if (Build.VERSION.SDK_INT >= 23) {
            val mPermissionList = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS)
            ActivityCompat.requestPermissions(this, mPermissionList, 123)
        }
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data)
    }

    override fun onDestroy() {
        super.onDestroy()
        UMShareAPI.get(this).release()
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
     fun onClick(v: View?) {
        when(v?.id){
            R.id.ll_VX->{//分享到微信
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform())

            }
            R.id.ll_pengyouquan->{//分享微信朋友圈
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform())
            }
            R.id.ll_QQ->{//分享到QQ好友
                share(SHARE_MEDIA.QQ.toSnsPlatform())
            }
            R.id.ib_back->{
                finish()
            }

        }
    }
    private fun share(platform: SnsPlatform){
        showLoadingDialog()
        if (shareUserBeans!=null){
            var web =UMWeb(shareUserBeans!!.url)
            web.setTitle(shareUserBeans?.share?.share_title)
            web.setThumb( UMImage(this, shareUserBeans?.share?.share_img))
            web.setDescription(shareUserBeans?.share?.share_content)
            ShareAction(this)
                    .withText("waterTheGreat")
                    .withMedia(web)
                    .setPlatform(platform.mPlatform)
                    .setCallback(umShareListener).share()
        }else{
            showToast("没有分享内容")
        }
        dismissLoadingDialog()
    }

    private var umShareListener= object : UMShareListener {
        override fun onResult(p0: SHARE_MEDIA?) {
            showToast("分享成功")
        }

        override fun onCancel(p0: SHARE_MEDIA?) {
            showToast("分享取消")
        }

        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
            showToast("分享失败")
        }

        override fun onStart(p0: SHARE_MEDIA?) {
        }
    }

}
