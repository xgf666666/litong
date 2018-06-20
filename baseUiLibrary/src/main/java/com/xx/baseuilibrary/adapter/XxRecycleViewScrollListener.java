package com.xx.baseuilibrary.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * XxRecycleViewScrollListener
 * 类描述: LinearLayoutManager 的RecycleView加载更多
 * 作者: LeiXiaoXing on 2017/1/6 12:41
 */

public abstract class XxRecycleViewScrollListener extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            //滑动停止状态时触发
            if (null == mLayoutManager) {
                mLayoutManager = recyclerView.getLayoutManager();
            }
            //赋值每页数据条数,默认20条
            int limit = getLimit() == 0 ? 20 : getLimit();

            if (mLayoutManager instanceof GridLayoutManager) {
                GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;
                if (gridLayoutManager.getItemCount() > limit && gridLayoutManager.findLastVisibleItemPosition() == mLayoutManager.getItemCount() - 1) {
                    //通知加载更多
                    onLoadMore();
                }
            } else if (mLayoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mLayoutManager;
                if (linearLayoutManager.getItemCount() > limit && linearLayoutManager.findLastVisibleItemPosition() == mLayoutManager.getItemCount() - 1) {
                    //通知加载更多
                    onLoadMore();
                }
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    /**
     * 获取每页数据条数
     * 默认20条
     */
    protected abstract int getLimit();

    /**
     * 触发加载更多
     */
    protected abstract void onLoadMore();
}
