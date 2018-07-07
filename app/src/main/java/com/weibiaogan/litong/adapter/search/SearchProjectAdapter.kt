package com.weibiaogan.litong.adapter.search

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.SearchProjectBean
import com.weibiaogan.litong.entity.StoreListBean
import com.weibiaogan.litong.extensions.loadImag

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class SearchProjectAdapter(datas : List<SearchProjectBean.DataBean>) : BaseQuickAdapter<SearchProjectBean.DataBean,BaseViewHolder>(R.layout.store_list_view,datas) {
    override fun convert(helper: BaseViewHolder?, item: SearchProjectBean.DataBean?) {
        /*helper?.setText(R.id.tv_store_list_name,item?.st_name)
        helper?.setText(R.id.tv_store_list_address,item?.st_address)
        helper?.setText(R.id.tv_store_list_type,item?.st_type)
        helper?.setText(R.id.tv_store_list_distance,item?.distance.toString())
        helper?.getView<ImageView>(R.id.iv_store_list_img)?.loadImag(item?.st_img!!,plach = R.mipmap.img_face)*/
    }
}