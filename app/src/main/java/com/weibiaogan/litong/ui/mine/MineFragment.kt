package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.os.Environment
import android.support.v4.widget.NestedScrollView
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.PermissionUtils
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
import com.weibiaogan.litong.ui.login.LoginActivity
import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecFragment
import com.xx.baseutilslibrary.common.ImageChooseHelper
import kotlinx.android.synthetic.main.fragment_mywine.*


/**
 * author: Gubr
 * date: 2018/5/7
 * describe:
 */
class MineFragment : BaseMvpLcecFragment<NestedScrollView, UserCenterBean, MineContract.Model, MineContract.View, MinePresenter>(), MineContract.View {
    override fun loadData(refresh: Boolean) {
        getPresenter().getData()
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
        iv_avatar.setOnPerCheckLoginClickListner {
            if (Constants.getUserData() != null) {
                startActivity(MyIntroActivity::class.java)
            }
        }


        view?.findViewById<View>(R.id.login_view)?.findViewById<TextView>(R.id.tv_login)?.setOnClickListener { startActivity(LoginActivity::class.java) }
        ll_wallet_area.setOnPerCheckLoginClickListner { toast("未写") }
        ll_orders_area.setOnPerCheckLoginClickListner { toast("未写") }
        ll_project_area.setOnPerCheckLoginClickListner { toast("跳发布") }
        ll_shop_area.setOnPerCheckLoginClickListner { toast("店铺入驻") }
        tv_service_phone.setOnClickListener { toast("打电话") }


    }


    private fun setUserIntro() {
        if (Constants.isLogin()) {
            val token = Constants.getToken()
        }
    }


    override fun setData(data: UserCenterBean?) {
        iv_avatar.loadImag(data?.user?.userImg
                ?: "", null, R.drawable.personal_center_, R.drawable.personal_center_)
        Constants.setUserData(data)
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
            showView(login_view)
//            showContent()
//            startActivity(LoginActivity::class.java)
        }
    }


}