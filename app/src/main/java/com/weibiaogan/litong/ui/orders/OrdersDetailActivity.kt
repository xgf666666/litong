package com.weibiaogan.litong.ui.orders

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.content.Intent
import android.text.TextUtils
import com.weibiaogan.litong.R
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
        var id = intent.getStringExtra("pt_id")
        if (!TextUtils.isEmpty(id)){
            getPresenter().orderDetail(id)
        }else{
            showToast("error id")
        }
//        showDialog()
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        //需求方评价记录
        //rl_order_jilu.setOnClickListener { startActivity(Intent(mContext, WorkEvaluateActivity::class.java)) }
        //把接单工人拉入黑名单
        //tv_heimingdan.setOnClickListener {  }
    }

    fun showDialog(){

        var view= View.inflate(mContext,R.layout.dialog_orderdetail,null)

        var dialog=AlertDialog.Builder(mContext)
        dialog.setView(view).show()
    }

    override fun getOrderDetail(bean: OrderDetailBean) {
        tv_title.text = bean.pt_name
        tv_time.text = "项目截止时间："+bean.end_time
        tv_price.text = "￥ "+bean.all_price
        tv_money_one.text = "￥ "+bean.first_price
        tv_money_two.text = "￥ "+bean.second_price
        tv_money_three.text = "￥ "+bean.three_price

        iv_person.loadImag(bean.boss_user.user_img)
        tv_name.text = bean.boss_user.nickname
        tv_phone.text = "联系方式："+bean.boss_user.user_phone

        tv_projectName.text ="项目名称："+ bean.pt_name
        tv_gongqi.text = "工期："+bean.add_time+"完成"
        tv_projectAdress.text = "项目地址："+bean.pt_address
        tv_style.text = "类型暂定"
        iv_detail_img.loadImag("")

        tv_person.text = "name"
        tv_times.text = "接单时间："
        tv_firstTime.text = "付首款时间："
        tv_twoTime.text = "付二期款时间："
        tv_lastTime.text = "付尾款时间："
    }


}
