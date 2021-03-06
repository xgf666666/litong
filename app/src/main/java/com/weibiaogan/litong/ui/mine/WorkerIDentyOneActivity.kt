package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Toast
import com.amap.api.maps2d.model.Text
import com.blankj.utilcode.util.EncodeUtils
import com.blankj.utilcode.util.PermissionUtils
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.dialog.widget.ActionSheetDialog
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.WorkerIdentyContract
import com.weibiaogan.litong.mvp.presenter.WorkeridentyPresenter
import com.weibiaogan.litong.ui.location.MapActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import kotlinx.android.synthetic.main.activity_worker_identy_one.*
import java.io.File
import java.io.Serializable

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:工人认证1
 */


class WorkerIDentyOneActivity : BaseMvpActivity<WorkeridentyPresenter>(),WorkerIdentyContract.View{


    var ONE:Int=1//设置第一张图片
    var TWO:Int=2//设置第二张图片
    var FLAG:Int=0
    var imgList:ArrayList<String>?=null//存放图片地址
    var fileOne: String?=null
    var fileTwo: String?=null
    var index:Int=0
    override fun setView(file: String) {
        imgList?.add(file)
        if (index==0){
            index=1
            ( getPresenter() as WorkeridentyPresenter).imgUp(fileTwo!!)
        }else{
            if (imgList?.size==2) {
                map?.put("idcard_img",imgList?.get(0)+","+imgList?.get(1))
                var intent=Intent(mContext,WorkerIDentyTwoActivity::class.java)
                intent.putExtra("map",  map as Serializable)
                dismissLoadingDialog()
                startActivity(intent)
                finish()
            }
        }
    }
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): WorkeridentyPresenter =WorkeridentyPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_worker_identy_one
    private lateinit var imageChooseHelper: ImageChooseHelper
    /**
     * 初始化数据状态
     */
    override fun initData() {
        imgList=ArrayList()
        initImageChooseHelper()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        iv_project_location.setOnClickListener{
            startActivityForResult(Intent(this, MapActivity::class.java),2)
        }
        bt_submit.setOnClickListener{
            getViewData() }
        rl_addOne.setOnClickListener {
            FLAG=ONE
            showEditAvatarDialog() }
        rl_addTwo.setOnClickListener {
            FLAG=TWO
            showEditAvatarDialog() }
    }
    //得到输入的值Map<String,String>
    var map:HashMap<String,String>?=null
    private fun getViewData(){
         map= HashMap<String,String>()
        if (TextUtils.isEmpty(et_name.text)|| fileOne==null||fileTwo==null||TextUtils.isEmpty(et_project_location.text)) {
            toast("请完善资料")
        }else if(et_phone.text.toString().length!=11|| TextUtils.isEmpty(et_phone.text)){
            toast("请输入正确手机号码")
        }else if (et_shenfenzheng.text.toString().length!=18|| TextUtils.isEmpty(et_shenfenzheng.text)) {
            toast("请输入正确的身份证")
        }else{
            map?.put("a_name",et_name.text.toString())
            map?.put("a_phone",et_phone.text.toString())
            map?.put("idcard_number",et_shenfenzheng.text.toString())
//            map?.put("idcard_img",imgList?.get(0)+","+imgList?.get(1))
            map?.put("a_address",et_project_location.text.toString())
            map?.put("lat_long",lots+","+lats)
            if (!TextUtils.isEmpty(et_danbaoren.text)){
                map?.put("a_guarantor",et_danbaoren.text.toString())
            }
            if (!TextUtils.isEmpty(et_danbaorenPhone.text)){
                map?.put("guarantor_phone",et_danbaorenPhone.text.toString())
            }
            showLoadingDialog()
            ( getPresenter() as WorkeridentyPresenter).imgUp(fileOne!!)

        }



    }


    /**
     * 显示图片弹窗
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
    private fun initImageChooseHelper(){
        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpActivity(this)
                .setAuthority("${BuildConfig.APPLICATION_ID}.fileprovider")//设置文件提供者
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/" + BuildConfig.APPLICATION_ID)//设置文件存储路径
                .isCrop(true)//开启裁剪
                .setCompressQuality(100)//压缩质量[1,100]
                .setSize(200, 200)//裁剪尺寸
                .setOnFinishChooseAndCropImageListener { bitmap, file ->
                    if (FLAG==ONE){
                        iv_oneView.setImageBitmap(bitmap)
                        iv_one.visibility=View.INVISIBLE
                        fileOne= EncodeUtils.base64Encode2String(file?.readBytes())
                    }else if (FLAG==TWO){
                        iv_twoView.setImageBitmap(bitmap)
                        fileTwo=EncodeUtils.base64Encode2String(file?.readBytes())
                        iv_two.visibility=View.INVISIBLE
                    }
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
