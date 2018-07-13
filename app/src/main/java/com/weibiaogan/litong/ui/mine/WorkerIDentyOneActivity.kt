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
    var img_one:String?=null//存放第一张推片地址
    var img_two:String?=null//存放第二张推片地址
    override fun setView(file: String) {
        if (FLAG==ONE){
            img_one=file
        }else{
            img_two=file
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
    private fun getViewData(){
        var map= HashMap<String,String>()
        if (TextUtils.isEmpty(et_name.text)||TextUtils.isEmpty(et_phone.text)||TextUtils.isEmpty(et_shenfenzheng.text)||
                TextUtils.isEmpty(img_one)||TextUtils.isEmpty(img_two)||TextUtils.isEmpty(et_project_location.text)||
                TextUtils.isEmpty(et_danbaoren.text)||TextUtils.isEmpty(et_danbaoren.text)||TextUtils.isEmpty(et_danbaorenPhone.text)) {
            toast("完善资料")
        }else if(et_phone.text.toString().length!=11||et_shenfenzheng.text.toString().length!=18||et_danbaorenPhone.text.toString().length!=11){
            toast("手机号码或身份证")
        }else{
            map.put("a_name",et_name.text.toString())
            map.put("a_phone",et_phone.text.toString())
//            Log.i("a_phone",img_one+","+img_two)
            map.put("idcard_number",et_shenfenzheng.text.toString())
            map.put("idcard_img",img_one+","+img_two)
            map.put("a_address",et_project_location.text.toString())
            map.put("lat_long",lots+","+lats)
            map.put("a_guarantor",et_danbaoren.text.toString())
            map.put("guarantor_phone",et_danbaorenPhone.text.toString())
            var intent=Intent(mContext,WorkerIDentyTwoActivity::class.java)
            intent.putExtra("map",  map as Serializable)
            startActivity(intent)
            finish()
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
                        iv_one.visibility=View.GONE
                        iv_oneView.setImageBitmap(bitmap)
                    }else if (FLAG==TWO){
                        iv_two.visibility= View.GONE
                        iv_twoView.setImageBitmap(bitmap)
                    }

                  getPresenter().fileStore(file)
                }
                .create()
    }
    var lots:String?=null
    var lats:String?=null
    var address:String?=null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2&&resultCode== MainActivity.RESULT_CODE){
            lots=data?.getStringExtra("location_log")!!
            lats=data?.getStringExtra("location_lat")!!
            address=data?.getStringExtra("location_address")!!
            et_project_location.setText(address)

        }else{
            imageChooseHelper.onActivityResult(requestCode, resultCode, data)

        }
    }


}
