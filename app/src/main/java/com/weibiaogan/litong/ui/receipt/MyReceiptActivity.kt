package com.weibiaogan.litong.ui.receipt

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.receipt.MyReceiptAdapter
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_store_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:  我的接单
 */
class MyReceiptActivity : BaseActivity(), BaseQuickAdapter.OnItemChildClickListener {
    var adapter : MyReceiptAdapter? = null

    override fun getActivityLayoutId(): Int = R.layout.activity_store_list

    override fun initData() {
        setTextBtn(true)
        tv_store_list_all.text = resources.getText(R.string.my_receipt_loading)
        tv_store_list_distance.text = resources.getText(R.string.my_receipt_finish)

        rv_store_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        adapter = MyReceiptAdapter(arrayListOf())
        rv_store_list_rv.adapter = adapter

        adapter?.setOnItemChildClickListener(this)
    }

    override fun initEvent() {

    }

    private fun setTextBtn(isAll: Boolean) {
        tv_store_list_all.isSelected = isAll
        tv_store_list_distance.isSelected = !isAll
        if(isAll){
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))
        }else{
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_normal))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_color_white))
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when(view?.id){
            R.id.tv_my_receipt_look -> startActivity(Intent(mContext,OrdersDetailActivity::class.java))
            R.id.tv_my_receipt_evaluate -> startActivity(Intent(mContext,EvaluateActivity::class.java))
        }
    }
}