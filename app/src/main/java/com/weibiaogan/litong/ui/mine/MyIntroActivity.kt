package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Intent
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import com.blankj.utilcode.util.PermissionUtils
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.dialog.widget.ActionSheetDialog
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.R
import com.weibiaogan.litong.aspect.CheckLogin
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.MyIntroContract
import com.weibiaogan.litong.mvp.presenter.MyIntroPresenter
import com.weibiaogan.litong.ui.login.ChangePWActivity
import com.weibiaogan.litong.ui.modify.ModifyBindActivity
import com.weibiaogan.litong.ui.pay.ChangePayPwActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import kotlinx.android.synthetic.main.activity_myintro.*

/**
 * author: Gubr
 * date: 2018/5/8
 * describe:
 */
class MyIntroActivity : BaseMvpActivity<MyIntroPresenter>(), MyIntroContract.View, View.OnClickListener {


    override fun createPresenter(): MyIntroPresenter {
        return MyIntroPresenter()
    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_myintro
    }

    override fun initData() {

        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpActivity(this)
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



        setDataUser(Constants.getUserData())

    }

    override fun initEvent() {
        iv_avatar.setOnClickListener(this)

        ll_change_login_pw.setOnClickListener(this)
        ll_change_pay_pw.setOnClickListener(this)
        ll_sex.setOnClickListener(this)
        ll_name.setOnClickListener(this)
        ll_change_phone_area.setOnClickListener(this)
    }

    override fun setData(o: UserCenterBean?) {
        Constants.setUserData(o)
        setDataUser(o)
    }

    fun setDataUser(o: UserCenterBean?) {
        if (o != null) {
            var sexStr = when (o.user.userSex) {
                1 -> {
                    "女"
                }
                2 -> {
                    "男"
                }
                else -> {
                    "未知"
                }
            }
            iv_avatar.loadImag(o.user.userImg, null, R.drawable.personal_center_, R.drawable.personal_center_)
            tv_name.text = o.user.nickname
            tv_sex.text = sexStr
            tv_phone.text = o.user.userPhone.replaceRange(4, 7, "****")
        }
    }

    @CheckLogin
    override fun onClick(v: View) {
        when (v.id) {
        //头像点击
            R.id.iv_avatar -> {
                showEditAvatarDialog()
            }
        //修改登录密码
            R.id.ll_change_login_pw -> {
                startActivity(ChangePWActivity::class.java)
            }
        //修改支付密码
            R.id.ll_change_pay_pw -> {
                startActivity(ChangePayPwActivity::class.java)
            }
        //修改性别
            R.id.ll_sex -> {
                showChangeSexDialog()
            }
        //修改昵称
            R.id.ll_name -> {
                startActivity(ChangeNicknameActivity::class.java)

            }
            R.id.ll_change_phone_area -> {
                startActivity(ModifyBindActivity::class.java)
            }
        }
    }

    override fun successful() {
        getPresenter().getUserData()
        toast("修改成功")
    }

    private lateinit var imageChooseHelper: ImageChooseHelper

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


    private fun showChangeSexDialog() {
        val items = arrayOf<String>("男", "女", "保密")
        val itemsInt = arrayOf("2", "1", "3")
        val dialog = ActionSheetDialog(this, items, null).isTitleShow(false)
        dialog
                .setOnOperItemClickL(object : OnOperItemClickL {
                    override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                        val sex = itemsInt[position]
                        getPresenter().updateUser(sex)
                        dialog.dismiss()
                    }
                })

        dialog.show()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        imageChooseHelper.onActivityResult(requestCode, resultCode, data)
    }


}