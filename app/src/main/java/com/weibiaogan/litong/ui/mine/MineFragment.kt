package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.support.v4.widget.NestedScrollView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.PermissionUtils
import com.bumptech.glide.Glide
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.R
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.extensions.setOnPerCheckLoginClickListner
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.MineContract
import com.weibiaogan.litong.mvp.presenter.MinePresenter
import com.weibiaogan.litong.ui.blacklist.BlacklistActivity
import com.weibiaogan.litong.ui.login.LoginActivity
import com.weibiaogan.litong.ui.pay.WalletActivity
import com.weibiaogan.litong.ui.project.PayCenterActivity
import com.weibiaogan.litong.ui.receipt.MyReceiptActivity
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import com.xx.baseutilslibrary.common.ImageChooseHelper
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager
import kotlinx.android.synthetic.main.fragment_mywine.*


/**
 * author: Gubr
 * date: 2018/5/7
 * describe:
 */
class MineFragment : BaseMvpLcecFragment<NestedScrollView, UserCenterBean, MineContract.Model, MineContract.View, MinePresenter>(), MineContract.View {
    var servicePhone:String?=null
    override fun addShop(phone: String?) {
        tv_service_phone.text=phone
        servicePhone=phone
    }
    override fun loadData(refresh: Boolean) {
        getPresenter().getData()
        getPresenter().addShop()
}


    override fun createPresenter(): MinePresenter {
        return MinePresenter()
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_mywine

    }

    override fun initView(view: View?) {
        super.initView(view)
        addViewtoViews(login_view)

    }

    override fun initEvent(view: View?) {
        rl_avatar.setOnPerCheckLoginClickListner {
            if (Constants.getUserData() != null) {
                startActivity(MyIntroActivity::class.java)
            }
        }
        view?.findViewById<View>(R.id.login_view)?.findViewById<TextView>(R.id.tv_login)?.setOnClickListener { startActivity(LoginActivity::class.java) }
        ll_wallet_area.setOnPerCheckLoginClickListner { startActivity(WalletActivity::class.java) }
        ll_orders_area.setOnPerCheckLoginClickListner { startActivity(MyReceiptActivity::class.java) }
        ll_project_area.setOnClickListener{ startActivity(Intent(context,MyPublishProjectActivity::class.java)) }
        ll_backlist_area.setOnPerCheckLoginClickListner { startActivity(BlacklistActivity::class.java) }
        ll_vip_area.setOnPerCheckLoginClickListner{
            if (Constants.getUserData()!=null){
                if (Constants.getUserData().user.grid==1){
                    PayCenterActivity.startPayCenter(mContext,"5","")
                }else{
                    showToast("你已是会员")
                }
            }
           }
        ll_shop_area.setOnPerCheckLoginClickListner {
           var intent=Intent(mContext,ShopAddActivity::class.java)
            intent.putExtra("phone",servicePhone)
            startActivity(intent) }
        ll_share_area.setOnClickListener{startActivity(ShareActivity::class.java)}
        tv_service_phone.setOnClickListener{
            val uri = Uri.parse("tel:"+servicePhone)
            val it = Intent(Intent.ACTION_DIAL, uri)
            startActivity(it)
        }
    }


    private fun setUserIntro() {
        if (Constants.isLogin()) {
            val token = Constants.getToken()
        }
    }


    override fun setData(data: UserCenterBean?) {
        Glide.with(this).load(Retrofit2Manager.instance.apiConfigProvider?.debugHost+data?.user?.userImg ?: "")
//                .fallback(R.drawable.personal_center_)
//                .placeholder(R.drawable.personal_center_)
                .error(R.drawable.personal_center_).into(iv_avatar)
        Constants.setUserData(data)
        tv_wallet.setText(""+data?.user?.balance)
        if (data?.user?.grid==2){
            tv_isVIP.setText(data?.user?.membersEnd)
            iv_avatar_vip.setImageResource(R.mipmap.ic_facetop_vip_y)
        }else{
            iv_avatar_vip.setImageResource(R.mipmap.ic_facetop_vip)
            tv_isVIP.setText("没开通")

        }
        showContent()
        when (data?.user?.userSex) {
            1 -> {
                iv_sex.setImageResource(R.drawable.male_)
            }
            2 -> {
                iv_sex.setImageResource(R.drawable.female_)
            }
            3 -> {
                iv_sex.setImageResource(R.drawable.weizhi_)
            }
            else -> {
                iv_sex.setImageResource(R.drawable.weizhi_)

            }
        }
        Log.i("dffssf",""+data?.user?.bossStat)
        Log.i("dffssf",""+data?.user?.workerStat)
        when(data?.user?.bossStat){

            1->{tv_label_need.setText("待审核需求方")
                tv_label_need.setSolid(R.color.bg_ddd)
            }
            2->{
                tv_label_need.setText("已认证需求方")

//                tv_label_need.setSolid(R.color.label_color_bg_green)
            }
            0->{
                tv_label_need.setText("待认证需求方")
                tv_label_need.setSolid(R.color.bg_ddd)
            }
        }
        when(data?.user?.workerStat){
            1->{tv_label_workers.setText("待审核工人")
                tv_label_workers.setSolid(R.color.bg_ddd)
            }
            2->{
                tv_label_workers.setText("已认证工人")
//                tv_label_workers.setSolid(R.color.label_color_bg_accent)
            }
            0->{
                tv_label_workers.setText("待认证工人")
                tv_label_workers.setSolid(R.color.bg_ddd)
            }
        }

        tv_name.text = data?.user?.nickname ?: ""


        val order = data?.order

    }

    override fun initData() {
        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpFragment(this)
                .setAuthority("${BuildConfig.APPLICATION_ID}.fileprovider")//设置文件提供者
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/" + BuildConfig.APPLICATION_ID)//设置文件存储路径
                .isCrop(true)//开启裁剪
                .setCompressQuality(100)//压缩质量[1,100]
                .setSize(120, 120)//裁剪尺寸
                .setOnFinishChooseAndCropImageListener { bitmap, file ->
                    //显示选好得图片
                    iv_avatar.setImageBitmap(bitmap)
                    //上传头像
                    getPresenter().fileStore(file)
                }
                .create()
    }

    /**
     * 显示修改头像弹窗
     */
    private fun showEditAvatarDialog() {
        //选图弹窗
        //请求相机和内存读取权限
        PermissionUtils.permission(Manifest.permission.CAMERA, Manifest.permission_group.STORAGE)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        //被给予权限,调起选图弹窗
                        ChooseImageDialogWrapper(imageChooseHelper)
                                .showChooseImage()
                    }

                    override fun onDenied() {
                        //被拒绝
                        showToast("拒绝给予权限会导致该功能不能正常使用")
                    }
                })
                .rationale { shouldRequest -> shouldRequest.again(true) }
                .request()
    }


    private lateinit var imageChooseHelper: ImageChooseHelper

    override fun onStart() {
        super.onStart()
        if (Constants.isLogin()) {
            if (Constants.getUserData() == null) {
                showLoading()

            }
            getPresenter().getData()
        } else {
            startActivity(LoginActivity::class.java)
            finishActivity()
            return
            showView(login_view)
            showContent()
        }
    }


}