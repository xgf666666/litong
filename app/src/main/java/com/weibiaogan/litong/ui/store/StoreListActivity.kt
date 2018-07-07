package com.weibiaogan.litong.ui.store

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.store.StoreListAdapter
import com.weibiaogan.litong.entity.StoreListBean
import com.xx.baseuilibrary.BaseActivity
import kotlinx.android.synthetic.main.activity_store_list.*

/**
 * author: HuaiXianZhong
 * date: 2018/7/5
 * describe:  店铺列表 页面
 */
class StoreListActivity : BaseActivity(), View.OnClickListener {

    var adapter : StoreListAdapter? = null

    override fun getActivityLayoutId(): Int = R.layout.activity_store_list

    override fun initData() {
        tv_store_list_all.setText(resources.getText(R.string.store_list_all))
        tv_store_list_distance.setText(resources.getText(R.string.store_list_distance))
        setTextBtn(true)
        var sList = arrayListOf<StoreListBean>()
        for (i in 0..10){
            var bean =  StoreListBean()
            bean.st_img = ""
            bean.distance = 700
            bean.st_name = "akfjsldg"
            bean.st_address = "jfdalkgnmgn"
            bean.st_type = "fasjdgl"
            sList.add(bean)
        }
        adapter = StoreListAdapter(sList)
        rv_store_list_rv.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        rv_store_list_rv.adapter = adapter

        adapter?.setOnItemClickListener { adapter, view, position -> startActivity(Intent(mContext,StoreDetailActivity::class.java)) }
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

    override fun initEvent() {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_store_list_all -> setTextBtn(true)
            R.id.tv_store_list_distance -> setTextBtn(false)
        }
    }
}