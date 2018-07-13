package com.weibiaogan.litong.ui.mine

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import com.flyco.dialog.listener.OnOperItemClickL
import com.flyco.dialog.widget.ActionSheetDialog
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.Worker
import com.weibiaogan.litong.extensions.toast
import com.weibiaogan.litong.mvp.contract.WorkerIdentyTwoContrat
import com.weibiaogan.litong.mvp.presenter.WorkerIdentyTwoPresenter
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_worker_identy_two.*


/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:工人认证2
 */
class WorkerIDentyTwoActivity : BaseMvpActivity<WorkerIdentyTwoPresenter>(),WorkerIdentyTwoContrat.View, CompoundButton.OnCheckedChangeListener {
    override fun succeful() {
        toast("成功")
        finish()
    }
     var has_invoice:Int=-1
    var is_company:Int=-1
    var is_card:Int=-1
    var is_insurance:Int=-1
    var map:HashMap<String,String>?=null
    var area_id:Int=0//工人ID

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked){
        when(buttonView?.id){
            //是否有车
            R.id.cb_carShi->{
                cb_carFou.isChecked=false
                is_card=1

            }
            R.id.cb_carFou->{
                cb_carShi.isChecked=false
                is_card=0
            }
            //是否有发票
            R.id.cb_fapiao->{
                cb_fapiaofou.isChecked=false
                has_invoice=1
            }
            R.id.cb_fapiaofou->{
                cb_fapiao.isChecked=false
                has_invoice=0
            }
            //保险情况
            R.id.cb_baoShi->{
                cb_baoFou.isChecked=false
                is_insurance=1
            }
            R.id.cb_baoFou->{
                cb_baoShi.isChecked=false
                is_insurance=0
            }
            R.id.cb_person->{
                cb_company.isChecked=false
                is_company=0
            }
            R.id.cb_company->{
                cb_person.isChecked=false
                is_company=1
            }
        }

        }


    }

    var datas:List<Worker>?=null
    override fun getListData(data: List<Worker>) {
        datas=data
    }


    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): WorkerIdentyTwoPresenter =WorkerIdentyTwoPresenter()

    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_worker_identy_two

    /**
     * 初始化数据状态
     */
    override fun initData() {
         map=intent.getSerializableExtra("map") as HashMap<String, String>
        getPresenter().getWorkerTyle()

    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
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
        cb_carShi.setOnCheckedChangeListener(this)
        cb_carFou.setOnCheckedChangeListener(this)
        cb_fapiao.setOnCheckedChangeListener(this)
        cb_fapiaofou.setOnCheckedChangeListener(this)
        cb_baoShi.setOnCheckedChangeListener(this)
        cb_baoFou.setOnCheckedChangeListener(this)
        cb_person.setOnCheckedChangeListener(this)
        cb_company.setOnCheckedChangeListener(this)
        bt_submit.setOnClickListener {
            addMap()

        }
    }
    private fun addMap(){
        if (map!=null){
            if (tv_select_one.equals("请选择")||tv_select_two.equals("请选择")||is_company==-1
                    ||is_card==-1||has_invoice==-1||is_insurance==-1||TextUtils.isEmpty(et_project_introduction.text.toString())||
                    TextUtils.isEmpty(et_congye.text.toString())||TextUtils.isEmpty(et_zhiye.text.toString())||
                    TextUtils.isEmpty(et_zhiye.text.toString())||TextUtils.isEmpty(et_service.text.toString())){
                toast("请完善资料")
            }else{
                map!!.put("area_id",""+area_id)
                map!!.put("is_company",""+is_company)
                map!!.put("has_card",""+is_card)
                map!!.put("has_invoice",""+has_invoice)
                map!!.put("price_start",""+et_fistPrice.text.toString())
                map!!.put("price_end",""+et_lastPrice.text.toString())
                map!!.put("experience",""+et_congye.text.toString())
                map!!.put("skill",""+et_zhiye.text.toString())
                map!!.put("service",""+et_service.text.toString())
                map!!.put("a_qualification",""+et_project_introduction.text.toString())
                getPresenter().renZhengWork(map as Map<String, String>)
            }
        }

    }
    //弹出选择框，设置第一个弹框
    private fun showChangeSexDialogOne(datas:List<Worker>) {
        val items=Array<String>(datas.size,{""})
        for (s in 0 ..(datas.size-1)){
            items.set(s,datas.get(s).area_name)
        }

        val dialog = ActionSheetDialog(this, items, null).isTitleShow(false)
        dialog.setOnOperItemClickL(object : OnOperItemClickL {
            override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                sex = items[position]
//                val s=itemsInt[position]
//                getPresenter().updateUserSex(s)
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

        val dialog = ActionSheetDialog(this, items, null).isTitleShow(false)
        dialog.setOnOperItemClickL(object : OnOperItemClickL {
            override fun onOperItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tv_select_two.setText(items[position])
                area_id=datas.get(item).son.get(position).area_id
                dialog.dismiss()
            }
        })

        dialog.show()


    }


}
