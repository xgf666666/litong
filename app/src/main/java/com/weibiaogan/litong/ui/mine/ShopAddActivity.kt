package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Context
import com.weibiaogan.litong.R
import com.weibiaogan.litong.mvp.contract.ShopAddContract
import com.weibiaogan.litong.mvp.presenter.ShopAddPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_shop_add.*
import android.content.Intent
import android.os.Environment
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.PermissionUtils
import com.bumptech.glide.Glide
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.ui.location.MapActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager
import kotlinx.android.synthetic.main.activity_shop_add.*


/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:商家入驻
 */

class ShopAddActivity : BaseMvpActivity<ShopAddPresenter>(),ShopAddContract.View {
    var filePath:String=""
    override fun setView(msg: String) {
        filePath=msg
        iv_add.visibility=View.INVISIBLE
//        iv_threeView.loadImag(msg)
        Log.i("dfafa",Retrofit2Manager.instance.apiConfigProvider?.debugHost+ msg)
        Glide.with(this).load(Retrofit2Manager.instance.apiConfigProvider?.debugHost+ msg)
                .placeholder(R.drawable.personal_center_)
                .error(R.drawable.personal_center_)
                .into(iv_threeView)
    }

    private lateinit var imageChooseHelper: ImageChooseHelper
    override fun addSucceeful(msg: String) {
        showToast(msg)
        dismissLoadingDialog()
        finish()
    }

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
        initImageChooseHelper()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_project_location.setOnClickListener{
            startActivityForResult(Intent(mContext, MapActivity::class.java),2)
        }
        rl_three.setOnClickListener{
            showEditAvatarDialog()
        }
        bt_submit.setOnClickListener{

            if (et_name.text.toString().isNullOrEmpty()){
                showToast("请输入店铺名称")
            } else if (et_type.text.toString().isNullOrEmpty()){
                showToast("请输入主营类别")
            } else if (et_project_location.text.toString().isNullOrEmpty()){
                showToast("店铺地址不能为空")
            } else if (et_time.text.toString().isNullOrEmpty()){
                showToast("请输入营业时间")
            } else if (et_phone.text.toString().isNullOrEmpty()){
                showToast("请输入店铺电话")
            }else if (filePath.isNullOrEmpty()){
                showToast("请添加店铺图片")
            }else{
                var map=HashMap<String,String>()
                map.put("st_name",et_name.text.toString())
                map.put("st_type",et_type.text.toString())
                map.put("st_address",et_project_location.text.toString())
                map.put("business_hours",et_time.text.toString())
                map.put("st_phone",et_phone.text.toString())
                map.put("st_img",filePath)
                map.put("lat_long",lots+","+lats)
                showLoadingDialog()
                getPresenter().addShop(map)
            }
        }
    }
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
    private fun initImageChooseHelper(){
        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpActivity(this)
                .setAuthority("${BuildConfig.APPLICATION_ID}.fileprovider")//设置文件提供者
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/" + BuildConfig.APPLICATION_ID)//设置文件存储路径
                .isCrop(true)//开启裁剪
                .setCompressQuality(100)//压缩质量[1,100]
                .setSize(200, 200)//裁剪尺寸
                .setOnFinishChooseAndCropImageListener { bitmap, file ->
                    getPresenter().imgUp(EncodeUtils.base64Encode2String(file?.readBytes()))
                }
                .create()
    }
    var lots:String?=null
    var lats:String?=null
    var address:String?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2&&resultCode== MainActivity.RESULT_CODE){
            lots=data?.getStringExtra("location_log")?:""
            lats=data?.getStringExtra("location_lat")?:""
            address=data?.getStringExtra("location_address")?:""
            et_project_location.setText(address)
        }else{
            imageChooseHelper.onActivityResult(requestCode, resultCode, data)

        }
    }



}
