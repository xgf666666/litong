package com.weibiaogan.litong.adapter.search

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.SearchProjectBean
import com.weibiaogan.litong.entity.StoreListBean
import com.weibiaogan.litong.extensions.loadImag
import com.weibiaogan.litong.utils.changeKm

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
class SearchProjectAdapter(datas : List<SearchProjectBean>) : BaseQuickAdapter<SearchProjectBean,BaseViewHolder>(R.layout.search_project_list_view,datas) {
    override fun convert(helper: BaseViewHolder?, item: SearchProjectBean) {
        helper?.setText(R.id.tv_search_project_name,item?.pt_name)
                ?.setText(R.id.tv_search_project_price,item?.all_price)
                ?.setText(R.id.tv_search_project_time,"截止时间 : "+item?.end_time)
                ?.setText(R.id.tv_search_project_distance,item?.distance.changeKm())//distance gone

        helper?.getView<ImageView>(R.id.iv_search_project_img)?.loadImag(item?.pt_imgs!!)
    }
}