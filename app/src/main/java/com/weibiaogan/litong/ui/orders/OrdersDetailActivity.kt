package com.weibiaogan.litong.ui.orders

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.content.Intent
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.Holder
import com.blankj.utilcode.util.ToastUtils
import com.flyco.dialog.listener.OnBtnClickL
import com.flyco.dialog.widget.NormalDialog
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.ImageBean
import com.weibiaogan.litong.entity.OrderDetailBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.mvp.contract.OrdersDetailCOntract
import com.weibiaogan.litong.mvp.presenter.OrderDetailPresenter
import com.weibiaogan.litong.ui.work.WorkEvaluateActivity
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_orders_detail.*
import kotlinx.android.synthetic.main.view_tab.*

/**
 * author: xiaoguangfei
 * date: 2018/7/5
 * describe:项目详情
 */
class OrdersDetailActivity : BaseMvpActivity<OrdersDetailCOntract.Presenter>(),OrdersDetailCOntract.View {

    companion object {
        fun startProjectDetail(context: Context,pt_id : String){
            val intent = Intent(context,OrdersDetailActivity::class.java)
            intent.putExtra("pt_id",pt_id)
            context.startActivity(intent)
        }
    }

    var mDetailImgs = arrayListOf<ImageView>()

    var mDetailBean : OrderDetailBean? = null

    var mPtId = ""

    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): OrderDetailPresenter =OrderDetailPresenter()
    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int = R.layout.activity_orders_detail

    /**
     * 初始化数据状态
     */
    override fun initData() {
        mPtId = intent.getStringExtra("pt_id")
        if (!TextUtils.isEmpty(mPtId)){
            getPresenter().orderDetail(mPtId)
        }else{
            showToast("error id")
        }

        mDetailImgs.add(iv_detail_img_one)
        mDetailImgs.add(iv_detail_img_two)
        mDetailImgs.add(iv_detail_img_three)
//        showDialog()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        //需求方评价记录
        rl_order_jilu.setOnClickListener { WorkEvaluateActivity.startWorkEvaluate(this@OrdersDetailActivity,1,mDetailBean?.user_id!!) }
        //把接单工人拉入黑名单
        tv_heimingdan.setOnClickListener { getPresenter().addBlack(mDetailBean?.pt_user_id.toString()) }

        //我要接单
        tv_receipt_project.setOnClickListener { showDialog() }
    }

    fun showDialog(){

        var dialog = NormalDialog(this)
        dialog.content("确认后请等待需求方确认")
                .title("确认订单")
                .style(NormalDialog.STYLE_TWO)
                .contentTextSize(17f)
                .titleTextSize(17f)
                .contentTextColor(resources.getColor(R.color.color888888))
                .titleTextColor(resources.getColor(R.color.color222222))
                .btnTextColor(resources.getColor(R.color.color3078EF),resources.getColor(R.color.color3078EF))
                .show()
        dialog.setOnBtnClickL(OnBtnClickL { dialog.dismiss() }, OnBtnClickL {
            dialog.dismiss()
            getPresenter().clickReceipt(mDetailBean?.pt_id.toString())
        })
    }

    override fun requestSuccess(msg: String) {
        showToast(msg)
        getPresenter().orderDetail(mPtId)   //刷新界面
    }

    override fun getOrderDetail(bean: OrderDetailBean) {
        mDetailBean = bean
        if (bean.pt_stat == 1 || bean.pt_stat == 7){
            tv_receipt_project.visibility = View.GONE   //不出现接单按钮
            showWork(bean.show_pt_user)
            tv_lazy_project.visibility = View.VISIBLE   //你来迟了 ，项目被接走了
            if (bean.add_blacklist == 1) tv_heimingdan.visibility = View.VISIBLE else tv_heimingdan.visibility = View.GONE
        }else if (bean.pt_stat == 2){
            tv_receipt_project.visibility = View.VISIBLE
            ll_old_work.visibility = View.GONE                //不会出现接单工人信息
            tv_lazy_project.visibility = View.GONE
        }else if (bean.pt_stat >= 3){
            tv_receipt_project.visibility = View.GONE
            showWork(bean.show_pt_user)                           //出现工人接单信息
            tv_heimingdan.visibility = View.GONE                  //不会出现加入黑名单按钮
            tv_lazy_project.visibility = View.VISIBLE   //你来迟了 ，项目被接走了
        }else{
            tv_lazy_project.visibility = View.VISIBLE   //你来迟了 ，项目被接走了
            tv_lazy_project.text = "该项目等待管理员审核中"
            tv_receipt_project.visibility = View.GONE   //不出现接单按钮
            ll_old_work.visibility = View.GONE                //不会出现接单工人信息
        }

        (iv_detail_head as ConvenientBanner<String>).setPages({ImageHolderView()},bean.pt_imgs).setPointViewVisible(true).startTurning(2000)         //轮播图

        tv_title.text = bean.pt_name                    //项目名字
        tv_time.text = "项目截止时间："+bean.end_time   //项目截止时间
        tv_price.text = "￥ "+bean.all_price              //项目价格
        tv_money_one.text = "￥ "+bean.first_price       //项目首款
        tv_money_two.text = "￥ "+bean.second_price           //项目二期款
        tv_money_three.text = "￥ "+bean.three_price            //项目尾款

        iv_person.loadImag(bean.boss_user.user_img,plach = R.mipmap.img_face)              //需求方img
        tv_name.text = bean.boss_user.nickname                   //需求方name
        tv_phone.text = "联系方式："+bean.boss_user.user_phone    //需求方phone

        tv_projectName.text ="项目描述："+ bean.pt_describe            //项目描述
        tv_gongqi.text = "工期："+bean.end_time+"完成"              //工期
        tv_projectAdress.text = "项目地址："+bean.pt_address             //项目地址
        tv_style.text = bean.area_id_data                                //类型

        for (i in 0 until mDetailImgs.size){
            if (i < bean.pt_imgs.size && !TextUtils.isEmpty(bean.pt_imgs[i])){
                mDetailImgs[i].loadImag(bean.pt_imgs[i],plach = R.mipmap.img_default)
            }else{
                mDetailImgs[i].visibility = View.GONE
            }
        }
    }

    /**
     * 显示接单工人信息
     */
    fun showWork(work : OrderDetailBean.ShowPtUserBean){
        ll_old_work.visibility = View.VISIBLE
        if (TextUtils.isEmpty(work.pt_user_id_time)){
            tv_times.visibility = View.GONE
        }
        if (TextUtils.isEmpty(work.first_pay_time)){
            tv_firstTime.visibility = View.GONE
        }
        if (TextUtils.isEmpty(work.second_pay_time)){
            tv_twoTime.visibility = View.GONE
        }
        if (TextUtils.isEmpty(work.three_pay_time)){
            tv_lastTime.visibility = View.GONE
        }
        tv_person.text = "接单工人："+work.worker_user                                     //工人 信息
        tv_times.text = "接单时间："+work.pt_user_id_time
        tv_firstTime.text = "付首款时间："+work.first_pay_time
        tv_twoTime.text = "付二期款时间："+work.second_pay_time
        tv_lastTime.text = "付尾款时间："+work.three_pay_time
    }

    /**
     * 轮播图
     */
    inner class ImageHolderView : Holder<String> {
        var imageview : ImageView? = null

        override fun UpdateUI(context: Context?, position: Int, data: String?) {
            imageview?.loadImag(data!!,plach = R.mipmap.img_default)
        }

        override fun createView(context: Context?): View {
            imageview = ImageView(this@OrdersDetailActivity)
            imageview?.scaleType = ImageView.ScaleType.FIT_XY
            return imageview as View
        }

    }

}
