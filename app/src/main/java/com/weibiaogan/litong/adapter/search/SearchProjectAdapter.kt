package com.weibiaogan.litong.adapter.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
        when(item.type){
            1->{//项目
                helper?.getView<TextView>(R.id.tv_search_project_price)?.visibility= View.VISIBLE
                helper?.getView<TextView>(R.id.tv_search_project_content)?.visibility= View.GONE
                helper?.getView<ImageView>(R.id.iv_location)?.visibility= View.GONE
                helper?.setText(R.id.tv_search_project_name,item?.pt_name)
                        ?.setText(R.id.tv_search_project_price,"￥ "+item?.all_price)
                        ?.setText(R.id.tv_search_project_time,"截止时间 : "+item?.end_time)
                        ?.setText(R.id.tv_search_project_distance,item?.distance.changeKm())
                helper?.getView<ImageView>(R.id.iv_search_project_img)?.loadImag(item?.pt_imgs!!,plach = R.mipmap.img_default)

            }
            2->{//工人
                helper?.getView<TextView>(R.id.tv_search_project_price)?.visibility= View.GONE
                helper?.getView<TextView>(R.id.tv_search_project_content)?.visibility= View.VISIBLE
                helper?.getView<ImageView>(R.id.iv_location)?.visibility= View.VISIBLE
                helper?.setText(R.id.tv_search_project_name,item?.nickname)
                        ?.setText(R.id.tv_search_project_time,item?.worker_address)
                        ?.setText(R.id.tv_search_project_distance,item?.distance.changeKm())
                        ?.setText(R.id.tv_search_project_content,"服务类型:"+item?.worker_service)
                helper?.getView<ImageView>(R.id.iv_search_project_img)?.loadImag(item?.user_img!!,plach = R.mipmap.img_default)

            }
            3->{//店铺
                helper?.getView<TextView>(R.id.tv_search_project_price)?.visibility= View.GONE
                helper?.getView<TextView>(R.id.tv_search_project_content)?.visibility= View.VISIBLE
                helper?.getView<ImageView>(R.id.iv_location)?.visibility= View.VISIBLE
                helper?.setText(R.id.tv_search_project_name,item?.st_name)
                        ?.setText(R.id.tv_search_project_time,item?.st_address)
                        ?.setText(R.id.tv_search_project_distance,item?.distance.changeKm())
                        ?.setText(R.id.tv_search_project_content,"主营类别:"+item?.st_type)
                helper?.getView<ImageView>(R.id.iv_search_project_img)?.loadImag(item?.st_img!!,plach = R.mipmap.img_default)

            }
        }

    }
}