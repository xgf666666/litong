package com.weibiaogan.litong.ui.receipt

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.blankj.utilcode.util.PermissionUtils
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.R
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.mvp.contract.EvaluateConstract
import com.weibiaogan.litong.mvp.presenter.EvaluatePresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import kotlinx.android.synthetic.main.activity_evaluate_work.*
import java.lang.StringBuilder

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe: 评论需求方
 */
class EvaluateActivity : BaseMvpActivity<EvaluateConstract.Presenter>(), View.OnClickListener , EvaluateConstract.View {

    var imageChooseHelper : ImageChooseHelper? = null

    var mBitmaps = arrayListOf<Bitmap>()
    var mImgs = arrayListOf<ImageView>()

    var type = 0   //0评论工人 1评论需求方
    var pt_id = ""

    var mImgUrl = arrayListOf<String>()

    companion object {
        fun startEvaluate(context: Context,type : Int,pt_id : String){
            var intent = Intent(context,EvaluateActivity::class.java)
            intent.putExtra("type",type)
            intent.putExtra("pt_id",pt_id)
            context.startActivity(intent)
        }
    }

    override fun createPresenter(): EvaluateConstract.Presenter {
        return EvaluatePresenter()
    }

    override fun getActivityLayoutId(): Int = R.layout.activity_evaluate_work

    override fun initData() {
        type = intent.getIntExtra("type", 0)
        pt_id = intent.getStringExtra("pt_id")
        when(type){
            0 -> tv_evaluate_title.text = resources.getText(R.string.evaluate_work_title)
            1 -> tv_evaluate_title.text = resources.getText(R.string.evaluate_demand_title)
        }

        sv_evaluate_score.setEvent(true)
        mImgs.add(iv_evaluate_add_img_three)
        mImgs.add(iv_evaluate_add_img_two)
        mImgs.add(iv_evaluate_add_img_one)
    }

    override fun initEvent() {
        iv_evaluate_add_img_one.setOnClickListener(this)
        tv_evaluate_confirm.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        var width = iv_evaluate_add_img_one.width

        Log.d("evaluate_img_width",width.toString() + ":::width")

        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpActivity(this)
                .setAuthority("${BuildConfig.APPLICATION_ID}.fileprovider")//设置文件提供者
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/" + BuildConfig.APPLICATION_ID)//设置文件存储路径
                .isCrop(true)//开启裁剪
                .setCompressQuality(100)//压缩质量[1,100]
                .setSize(width, 106)//裁剪尺寸
                .setOnFinishChooseAndCropImageListener { bitmap, file ->
                    //                    显示选好得图片
                    //iv_avatar.setImageBitmap(bitmap)

                    //上传头像
                    //getPresenter().fileStore(file)
                    getPresenter().fileStore(file)
                    showImage(bitmap)
                }
                .create()

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_evaluate_add_img_one -> showEditAvatarDialog()
            R.id.tv_evaluate_confirm -> pullEvaluate()
        }
    }

    /**
     * 上传评论数据
     */
    fun pullEvaluate(){
        var content = et_evaluate_edit.text.toString().trim()
        var score = sv_evaluate_score.starNum
        var imgs = ""
        for (i in 0 until mImgUrl.size){
            imgs + mImgUrl[i] + ","
        }
        if (TextUtils.isEmpty(content)){
            content = ""
        }else if (!TextUtils.isEmpty(imgs)){
            imgs = imgs.substring(0,imgs.length-1)
        }
        if (type == 0){
            getPresenter().evaluateWork(pt_id,content,imgs,score.toString())
        }else if (type == 1){
            getPresenter().evaluateBoss(pt_id,content,imgs,score.toString())
        }
    }

    /**
     * 显示选择的图片
     */
    fun showImage(bitmap:Bitmap){
        mBitmaps.add(bitmap)
        if (mBitmaps.size == 1){
            mImgs[1].visibility = View.VISIBLE
            mImgs[1].setImageBitmap(bitmap)
            return
        }
        for (i in 0 until mImgs.size){
            if (i < mBitmaps.size){
                mImgs[i].visibility = View.VISIBLE
                mImgs[i].setImageBitmap(mBitmaps[i])
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
                        ChooseImageDialogWrapper(imageChooseHelper!!)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        imageChooseHelper?.onActivityResult(requestCode, resultCode, data)
    }

    override fun evaluateSuccess(msg: String) {
        showToast(msg)
    }

    override fun getImgUrl(url: String) {
        mImgUrl.add(url)
    }


}