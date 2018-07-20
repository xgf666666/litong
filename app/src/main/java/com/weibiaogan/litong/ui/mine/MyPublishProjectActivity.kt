package com.weibiaogan.litong.ui.mine

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.weibiaogan.litong.App
import com.weibiaogan.litong.R
import com.weibiaogan.litong.adapter.mine.MyPublishProjectAdapter
import com.weibiaogan.litong.entity.PublicProjectBean
import com.weibiaogan.litong.mvp.contract.MyPublishProjectContract
import com.weibiaogan.litong.mvp.presenter.MyPublishProjectPresenter
import com.weibiaogan.litong.ui.orders.OrdersDetailActivity
import com.weibiaogan.litong.ui.project.PayCenterActivity
import com.weibiaogan.litong.ui.receipt.EvaluateActivity
import com.weibiaogan.litong.utils.addData
import com.xx.baseuilibrary.mvp.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_store_list.*

/**
 * author: xiaoguagnfei
 * date: 2018/7/6
 * describe:我的发布
 */
class MyPublishProjectActivity : BaseMvpActivity<MyPublishProjectPresenter>(),MyPublishProjectContract.View, View.OnClickListener, OnRefreshLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {

    var adapter = MyPublishProjectAdapter(arrayListOf())
    var mCurrentPage = 1
    var mStat = 1


    /**
     * 创建P层
     *
     * @return P层对象
     */
    override fun createPresenter(): MyPublishProjectPresenter=MyPublishProjectPresenter()


    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    override fun getActivityLayoutId(): Int =R.layout.activity_store_list

    /**
     * 初始化数据状态
     */
    override fun initData() {
        tv_store_list_title.text = "我的发布"

        tv_store_list_all.text = "进行中"
        tv_store_list_distance.text = "已完成"
        tv_store_list_all.isSelected = true
        tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
        tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))

        rv_store_list_rv.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false)
        rv_store_list_rv.adapter = adapter

        getPresenter().bossProjectList(mStat.toString(),mCurrentPage.toString())
    }

    private fun setTextBtn(isAll: Boolean) {
        if ((isAll && mStat == 1) || (!isAll && mStat == 2)){
            return
        }
        mCurrentPage = 1
        tv_store_list_all.isSelected = isAll
        tv_store_list_distance.isSelected = !isAll
        if(isAll){
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_color_white))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_normal))
            mStat = 1
        }else{
            tv_store_list_all.setTextColor(resources.getColor(R.color.text_normal))
            tv_store_list_distance.setTextColor(resources.getColor(R.color.text_color_white))
            mStat = 2
        }
        getPresenter().bossProjectList(mStat.toString(),mCurrentPage.toString())
    }

    /**
     * 初始化事件
     */
    override fun initEvent() {
        tv_store_list_all.setOnClickListener(this)
        tv_store_list_distance.setOnClickListener(this)
        refresh_store_list.setOnRefreshLoadMoreListener(this)

        adapter.setOnItemChildClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_store_list_all -> setTextBtn(true)
            R.id.tv_store_list_distance -> setTextBtn(false)
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        mCurrentPage = 1
        getPresenter().bossProjectList(mStat.toString(),mCurrentPage.toString())
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        getPresenter().bossProjectList(mStat.toString(),(++mCurrentPage).toString())
    }

    /**
     * 我的发布 结果
     */
    override fun getBossProjectList(data: PublicProjectBean) {
        refresh_store_list.addData(adapter,data.data)
    }

    /**
     * 取消项目
     */
    override fun cancelProject(msg: String) {
        showToast(msg)
    }


    /**
     * adapter item click
     */
    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        var bean = (adapter as MyPublishProjectAdapter).data[position]
        if (view?.id == R.id.tv_one){   //取消项目
            getPresenter().cancelProject(bean.pt_id.toString())
        }else if (view?.id == R.id.tv_two){
            when(bean.pt_stat){
                1,7 -> {
                    if (bean.boss_comments == 0){   //评论工人
                        EvaluateActivity.startEvaluate(mContext,0,bean.pt_id.toString())
                    }
                }
                3 -> {
                    SureWorkerActivity.startSureWork(this@MyPublishProjectActivity,bean.pt_user_id.toString(),bean.pt_id.toString(),bean.first_price.toString()) }  //确认工人
                4 -> {
                    PayCenterActivity.startPayCenter(this@MyPublishProjectActivity,"2",bean.pt_id.toString(),bean.first_price) } //付收款
                5 -> {
                    PayCenterActivity.startPayCenter(this@MyPublishProjectActivity,"3",bean.pt_id.toString(),bean.second_price) } //付二期款
                6 -> {
                    PayCenterActivity.startPayCenter(this@MyPublishProjectActivity,"4",bean.pt_id.toString(),bean.three_price)} //付尾款
                0 -> PayCenterActivity.startPayCenter(this@MyPublishProjectActivity,"1",bean.pt_id.toString(),bean.prepaid_price) //预支付金额

            }
        }else if (view?.id == R.id.tv_three){   //查看项目
            OrdersDetailActivity.startProjectDetail(mContext,bean.pt_id.toString())
        }
        mCurrentPage = 1
        getPresenter().bossProjectList(mStat.toString(),mCurrentPage.toString())
    }


}
