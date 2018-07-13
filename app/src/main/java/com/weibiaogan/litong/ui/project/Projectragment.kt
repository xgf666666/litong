package com.weibiaogan.litong.ui.project

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.blankj.utilcode.util.PermissionUtils
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.dialog.widget.ActionSheetDialog
import com.weibiaogan.litong.BuildConfig
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.IsPublic
import com.weibiaogan.litong.entity.PublicWorker
import com.weibiaogan.litong.entity.Worker
import com.weibiaogan.litong.extensions.getTString
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.extensions.setOnPerCheckLoginClickListner
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.ProjectContract
import com.weibiaogan.litong.mvp.presenter.ProjectPresenter
import com.xx.baseuilibrary.mvp.BaseMvpFragment
import com.weibiaogan.litong.ui.location.GeoToScreenActivity
import com.weibiaogan.litong.ui.location.MapActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import com.xx.baseutilslibrary.network.entity.BaseResponseEntity
import kotlinx.android.synthetic.main.fragment_project.*
import java.io.File


/**
 * author: Gubr
 * date: 2018/5/7
 * describe:
 */
class Projectragment : BaseMvpFragment<ProjectContract.Model, ProjectContract.View, ProjectPresenter>(), ProjectContract.View, View.OnClickListener, MainActivity.PhotoListener {

    companion object {

        const val RESULT_CODE = 0x15
    }

    var baozhengjing:String?=null
    override fun isPublic(isPublic: BaseResponseEntity<IsPublic>) {//是否可以发布
        Log.i("statssss",""+isPublic.data?.stat)
        if (isPublic.data?.stat!=3){
            showDialog(isPublic.msg!!)
        }
    }

    override fun setWorker(publicWorker: PublicWorker) {
        datas=publicWorker.area
        baozhengjing=""+publicWorker.prepaid.proportion
        et_project_first_ratio.setText(""+publicWorker.proportion.frist)
        et_project_second_ratio.setText(""+publicWorker.proportion.second)
        tv_wei.setText(""+publicWorker.proportion.three+"%，保证金:"+baozhengjing+"%")
    }
    var datas:List<Worker>?=null
    private lateinit var imageChooseHelper: ImageChooseHelper
    var ONE:Int=1//设置第一张图片
    var TWO:Int=2//设置第二张图片
    var THREE:Int=3//设置第三张图片
    var FLAG:Int=0
    var img_one:String?=null//存放第一张推片地址
    var img_two:String?=null//存放第二张推片地址
    var img_three:String?=null//存放第三张推片地址
    var area_id:Int=0//工人ID
    override fun setView(file: String) {
        if (img_one.isNullOrEmpty()){
            img_one=file
            iv_oneView.loadImag(BuildConfig.DEV_DOMAIN+"/"+file, null, 0, 0)
            rl_one.visibility=View.VISIBLE

        }else if (img_two.isNullOrEmpty()){
            img_two=file
            iv_twoView.loadImag(BuildConfig.DEV_DOMAIN+"/"+file, null, 0, 0)
            rl_two.visibility=View.VISIBLE
        }else{
            img_three =file
            iv_threeView.visibility=View.VISIBLE
            iv_threeView.loadImag(BuildConfig.DEV_DOMAIN+"/"+file, null, 0, 0)
            iv_add.setImageResource(R.drawable.ic_delete)
        }


    }
    override fun createPresenter(): ProjectPresenter {
        return ProjectPresenter()
    }

    override fun getFragmentLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun initView(view: View?) {
        getPresenter().isPublic()
        getPresenter().getWorkerTyle()
        var acs=activity as MainActivity
        acs.setPhoto(this)
    }
    //回调接口
    override fun setPresenter(bitmap: Bitmap, file: File) {
        getPresenter().fileStore(file)
    }

    override fun initEvent(view: View?) {
        rl_project_worker_selector_1.setOnClickListener {
            if (datas!=null){
                showChangeSexDialogOne(datas!!)
            }
        }

        rl_project_worker_selector_2.setOnClickListener {
            if (item == 100000) {
                toast("请先选择左边的类型")
            } else {
                showChangeSexDialogTwo(datas!!)
            }
        }
        bt_submit.setOnPerCheckLoginClickListner {
        pullProject()
        }
        iv_project_location.setOnClickListener {
            activity?.startActivityForResult(Intent(mContext,MapActivity::class.java),2)
        }
        ll_xuzhi.setOnClickListener{
            startActivity(ProjectPublicNoteActivity::class.java)
        }
        iv_add.setOnClickListener{

            if (img_one.isNullOrEmpty()||img_two.isNullOrEmpty()||img_three.isNullOrEmpty()){
                var acs=activity as MainActivity
                acs.showEditAvatarDialog()
            }else{
                img_three=null
                iv_threeView.visibility=View.INVISIBLE
                iv_add.setImageResource(R.mipmap.add_img)
            }
        }
        iv_delOne.setOnClickListener{
            img_one=null
            rl_one.visibility=View.INVISIBLE
        }
        iv_delTwo.setOnClickListener{
            img_two=null
            rl_two.visibility=View.INVISIBLE
        }
        et_project_first_ratio.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var two=et_project_second_ratio.getTString()!!
                var one=s.toString()
                var oneInt:Int=0
                var twoInt:Int=0
                if (one.isNullOrEmpty()){
                    oneInt=0
                }else{
                    oneInt=one.toInt()
                }
                if (two.isNullOrEmpty()){
                    twoInt=0
                }else{
                    twoInt=two.toInt()
                }
                tv_wei.setText("尾款比例为:"+(100-twoInt-oneInt)+"%，保证金:"+baozhengjing+"%")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        et_project_second_ratio.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var two=s.toString()!!
                var one=et_project_first_ratio.getTString()
                var oneInt:Int=0
                var twoInt:Int=0
                if (one.isNullOrEmpty()){
                    oneInt=0
                }else{
                    oneInt=one.toInt()
                }
                if (two.isNullOrEmpty()){
                    twoInt=0
                }else{
                    twoInt=two.toInt()
                }
                tv_wei.setText("尾款比例为:"+(100-twoInt-oneInt)+"%，保证金:"+baozhengjing+"%")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun successful() {
        toast("发布成功")
    }


    private fun pullProject(){
        val projectName = et_project_name.getTString()
        val projectEndtime = et_project_endTime.getTString()
        val projectLocation = et_project_location.getTString()
        val projectCost = et_project_cost.getTString()
        val projectFirstRatio = et_project_first_ratio.getTString()
        val projectSecondRatio = et_project_second_ratio.getTString()
        val projectIntroduction = et_project_introduction.getTString()
        val projectAddress=et_project_location.getTString()
        val projectAreaId=""+area_id
        var one:Int= projectFirstRatio.toIntOrNull()!!
        var two:Int=projectSecondRatio.toIntOrNull()!!
        val projectLastRatio=""+(100-one-two)
        val projectLanlog=log+","+lag
        var ptImgs:String=""
        if (!img_one.isNullOrEmpty())
            ptImgs=ptImgs+","+img_one
        if (!img_two.isNullOrEmpty())
            ptImgs=ptImgs+","+img_two
        if (!img_three.isNullOrEmpty())
            ptImgs=ptImgs+","+img_three
        if (cb_note.isChecked){
            getPresenter().pullProject(projectName,projectIntroduction
                    ,projectEndtime,projectAddress,projectAreaId
                    ,projectFirstRatio,projectSecondRatio
                    ,projectLastRatio,ptImgs,projectLanlog,projectCost)

        }else{
            toast("请阅读项目发布须知")
        }
    }

    override fun initData() {
    }

    override fun onClick(v: View) {
    }
    //弹出选择框，设置第一个弹框
    private fun showChangeSexDialogOne(datas:List<Worker>) {
        val items=Array<String>(datas.size,{""})
        for (s in 0 ..(datas.size-1)){
            items.set(s,datas.get(s).area_name)
        }

        val dialog = ActionSheetDialog(mContext, items, null).isTitleShow(false)
        dialog.setOnOperItemClickL(object : OnOperItemClickL {
            override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                item=position
                tv_select_one.setText(items[position])
                tv_select_two.setText("请选择")
                dialog.dismiss()
            }
        })

        dialog.show()


    }
    //标记第一个工人类型选择的类型
    var item:Int=100000
    //弹出选择框，设置第二个弹框
    private fun showChangeSexDialogTwo(datas:List<Worker>) {
        val items=Array<String>(datas.get(item).son.size,{""})
//        Log.i("size",""+datas.get(item).son.size)
        for (s in 0 ..(datas.get(item).son.size-1)){
            items.set(s,datas.get(item).son.get(s).area_name)
        }

        val dialog = ActionSheetDialog(mContext, items, null).isTitleShow(false)
        dialog.setOnOperItemClickL(object : OnOperItemClickL {
            override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tv_select_two.setText(items[position])
                dialog.dismiss()
                area_id=datas.get(item).son.get(position).area_id
            }
        })

        dialog.show()


    }
    fun showDialog(text:String) {
        var view = View.inflate(mContext, R.layout.dialog_public, null)
        var tv_contents=view.findViewById<TextView>(R.id.tv_contents)
        tv_contents.setText(text)
        var dialog = AlertDialog.Builder(mContext).create()
        dialog.setView(view)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }
    var log:String?=null
    var lag:String?=null
    public fun setMap(logs:String,lags:String ,address:String){
        et_project_location.setText(address)
        log=logs
        lag=lags
        Log.i("aggsrfgfgf",address+log+lag)

    }


}