package com.weibiaogan.litong.ui.mine

import android.Manifest
import android.content.Intent
import android.os.Environment
import android.text.TextUtils
import android.view.View
import android.widget.CompoundButton
import com.blankj.utilcode.util.PermissionUtils
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.BosIdentyContract
import com.weibiaogan.litong.mvp.presenter.BosIdentyPresenter
import com.weibiaogan.litong.ui.location.MapActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import kotlinx.android.synthetic.main.activity_bos_identy.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:需求方认证
 */
class BosIdentyActivity : BaseMvpActivity<BosIdentyPresenter>(),BosIdentyContract.View, CompoundButton.OnCheckedChangeListener {

    var is_company:Int=-1//判断是否选择公司
    var is_card:Int=-1//判断是否有车
    var is_insurance:Int=-1//判断是否有保险
    var ONE:Int=1//设置第一张图片
    var TWO:Int=2//设置第二张图片
    var FLAG:Int=0
    var img_one:String?=null//存放第一张推片地址
    var img_two:String?=null//存放第二张推片地址
    var imgList:ArrayList<String>?=null

    override fun setView(data: String) {
//        if (FLAG==ONE){
//            img_one=data
//        }else{
//            img_two=data
//        }
        imgList?.add(data)
    }
    override fun identySucceful() {
        toast("审核提交成功")
        finish()
    }
    private lateinit var imageChooseHelper: ImageChooseHelper
    /**
     * 回调方法
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if(isChecked){


        when(buttonView?.id){
            R.id.cb_carShi->{
                is_card=1
                cb_carFou.isChecked=false
            }
            R.id.cb_carFou->{
                is_card=0
                cb_carShi.isChecked=false
            }
            R.id.cb_baoShi->{
                is_insurance=1
                cb_baoFou.isChecked=false
            }
            R.id.cb_baoFou->{
                is_insurance=0
                cb_baoShi.isChecked=false
            }
            R.id.cb_person->{
                is_company=0
                cb_company.isChecked=false
            }
            R.id.cb_company->{
                is_company=1
                cb_person.isChecked=false
            }
        }
        }
    }

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_bos_identy
    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): BosIdentyPresenter=BosIdentyPresenter()

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
        cb_carShi.setOnCheckedChangeListener(this)
        cb_carFou.setOnCheckedChangeListener(this)
        cb_baoShi.setOnCheckedChangeListener(this)
        cb_baoFou.setOnCheckedChangeListener(this)
        cb_person.setOnCheckedChangeListener(this)
        cb_company.setOnCheckedChangeListener(this)
        bt_submit.setOnClickListener{
            startactivity()
        }
        iv_one.setOnClickListener{
            FLAG=ONE
            showEditAvatarDialog()
        }
        iv_two.setOnClickListener{
            FLAG=TWO
            showEditAvatarDialog()
        }
        iv_project_location.setOnClickListener{
            startActivityForResult(Intent(this,MapActivity::class.java),2)
        }
    }
    private fun startactivity(){
       var map=HashMap<String,String>()
        if (TextUtils.isEmpty(et_name.text)|| TextUtils.isEmpty(et_phone.text)|| TextUtils.isEmpty(et_shenfenzheng.text)||
                imgList?.size!=2|| TextUtils.isEmpty(et_project_location.text)||
                TextUtils.isEmpty(et_danbaoren.text)|| TextUtils.isEmpty(et_danbaoren.text)|| TextUtils.isEmpty(et_danbaorenPhone.text)||
                is_company==-1 ||is_card==-1||is_insurance==-1) {
            toast("完善资料")
        }else if(et_phone.text.toString().length!=11||et_shenfenzheng.text.toString().length!=18||et_danbaorenPhone.text.toString().length!=11){
            toast("手机号码或身份证")
        } else{
            map.put("idcard_img",imgList?.get(0)+","+imgList?.get(1))
            map.put("idcard_number",et_shenfenzheng.text.toString())
            map.put("a_name",et_name.text.toString())
            map.put("a_phone",et_phone.text.toString())
            map.put("a_qualification",et_project_introduction.text.toString())
            map.put("a_guarantor",et_danbaoren.text.toString())
            map.put("a_address",et_project_location.text.toString())
            map.put("is_company",""+is_company)
            map.put("has_card",""+is_card)
            map.put("has_insurance",""+is_insurance)
            map.put("lat_long",lots+","+lats)
            map.put("guarantor_phone",et_danbaorenPhone.text.toString())
            getPresenter().bosIdenty(map)
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
                        iv_one.visibility= View.GONE
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
            lots=data?.getStringExtra("location_log")?:""
            lats=data?.getStringExtra("location_lat")?:""
            address=data?.getStringExtra("location_address")?:""
            et_project_location.setText(address)
        }else{
            imageChooseHelper.onActivityResult(requestCode, resultCode, data)

        }
    }


}
