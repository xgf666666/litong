package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.blankj.utilcode.util.PermissionUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.dialog.widget.ActionSheetDialog
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.R
import com.weibiaogan.litong.R.id.iv_avatar
import com.weibiaogan.litong.aspect.CheckLogin
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.entity.UserCenterBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.extensions.setOnPerCheckLoginClickListner
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.MyIntroContract
import com.weibiaogan.litong.mvp.presenter.MyIntroPresenter
import com.weibiaogan.litong.ui.login.ChangePWActivity
import com.weibiaogan.litong.ui.modify.ModifyBindActivity
import com.weibiaogan.litong.ui.pay.ChangePayPwActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager
import kotlinx.android.synthetic.main.activity_myintro.*

/**
 * author: Gubr
 * date: 2018/5/8
 * describe:我的信息
 */
class MyIntroActivity : BaseMvpActivity<MyIntroPresenter>(), MyIntroContract.View, View.OnClickListener {



    override fun createPresenter(): MyIntroPresenter {
        return MyIntroPresenter()
    }

    override fun getActivityLayoutId(): Int {
        return R.layout.activity_myintro
    }

    override fun initData() {
        if (Constants.getUserData().balanceHas==1){
            tv_pay.setText("修改")
        }
        Glide.with(this).load(Retrofit2Manager.instance.apiConfigProvider?.debugHost+Constants.getUserData()?.user?.userImg ?: "")
//                    .fallback(R.drawable.personal_center_)
                .placeholder(R.drawable.personal_center_)
                .error(R.drawable.personal_center_)
                .into(iv_avatar)
        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpActivity(this)
                .setAuthority("${BuildConfig.APPLICATION_ID}.fileprovider")//设置文件提供者
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/" + BuildConfig.APPLICATION_ID)//设置文件存储路径
                .isCrop(true)//开启裁剪
                .setCompressQuality(100)//压缩质量[1,100]
                .setSize(120, 120)//裁剪尺寸
                .setOnFinishChooseAndCropImageListener { bitmap, file ->
//                    显示选好得图片
                    iv_avatar.setImageBitmap(bitmap)
                    //上传头像
                    getPresenter().fileStore(file)
                }
                .create()
    }

    override fun onResume() {
        super.onResume()
        setDataUser(Constants.getUserData())
    }

    override fun initEvent() {
        ll_touxiang.setOnClickListener(this)
        ll_change_login_pw.setOnClickListener(this)
        ll_change_pay_pw.setOnClickListener(this)
        ll_sex.setOnClickListener(this)
        ll_name.setOnClickListener(this)
        ll_change_phone_area.setOnClickListener(this)
        ll_bos_renzheng.setOnClickListener(this)
        ll_workRenzheng.setOnClickListener(this)
        tv_back.setOnClickListener(this)
    }

    override fun setData(o: UserCenterBean?) {
        Constants.setUserData(o)
        setDataUser(o)
    }

    fun setDataUser(o: UserCenterBean?) {
        if (o != null&&o.user!=null) {
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

            tv_name.text = o.user.nickname
            tv_sex.text = sexStr
            tv_phone.text = Constants.getPhone().replaceRange(4, 7, "***")
        }
        if (o?.user?.workerStat==0){
            tv_worker.setText("去认证")
        } else if (o?.user?.workerStat==1){
                tv_worker.setText("审核中")
            }else if(o?.user?.workerStat==2){
                tv_worker.setText("已认证")
            }

        if (o?.user?.bossStat==0){
            tv_boss.setText("去认证")
        } else if (o?.user?.bossStat==1){
            tv_boss.setText("审核中")
        }else if(o?.user?.bossStat==2){
            tv_boss.setText("已认证")
        }
    }

    @CheckLogin
    override fun onClick(v: View) {
        when (v.id) {
        //头像点击
            R.id.ll_touxiang -> {
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
            //修改手机号码
            R.id.ll_change_phone_area -> {
                startActivity(ModifyBindActivity::class.java)
            }
            //退出登录
            R.id.tv_back->{
                    getPresenter().loginOff()
                }
            R.id.ll_bos_renzheng->{
                if (Constants.getUserData().user.bossStat==1){
                    toast("你已提交需求方审核")
                }else if(Constants.getUserData().user.bossStat==2){
                    toast("你已认证需求方")
                }else{
                    startActivity(BosIdentyActivity::class.java)
                }


            }
            R.id.ll_workRenzheng->{
                if (Constants.getUserData().user.workerStat==1){
                    toast("你已提交工人审核")
                }else if(Constants.getUserData().user.workerStat==2){
                    toast("你已认证工人")
                }else{
                    startActivity(WorkerIDentyOneActivity::class.java)
                }
            }

        }
    }

    override fun successful(fanhui: String?) {
//        iv_avatar.loadImag(BuildConfig.DEV_DOMAIN+"/"+fanhui, null, R.drawable.personal_center_, R.drawable.personal_center_)

    }
    override fun sexSuccessful() {
        tv_sex.setText(sex)
    }
    override fun loginOff() {
        toast("退出成功")
        Constants.loginOut()
        finish()
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

   lateinit var sex:String
    private fun showChangeSexDialog() {
        val items = arrayOf<String>("男", "女")
        val itemsInt = arrayOf("2", "1")
        val dialog = ActionSheetDialog(this, items, null).isTitleShow(false)
        dialog.setOnOperItemClickL(object : OnOperItemClickL {
                    override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                         sex = items[position]
                        val s=itemsInt[position]
                        getPresenter().updateUserSex(s)
                        dialog.dismiss()
                    }
                })

        dialog.show()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        imageChooseHelper.onActivityResult(requestCode, resultCode, data)
    }


}