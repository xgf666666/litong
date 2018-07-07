package com.weibiaogan.litong.ui.receipt

import com.weibiaogan.litong.R
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_store_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:  我的接单
 */
class MyReceiptActivity : BaseActivity() {
    override fun getActivityLayoutId(): Int = R.layout.activity_store_list

    override fun initData() {
        setTextBtn(true)
        tv_store_list_all.text = resources.getText(R.string.my_receipt_loading)
        tv_store_list_distance.text = resources.getText(R.string.my_receipt_finish)


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
}