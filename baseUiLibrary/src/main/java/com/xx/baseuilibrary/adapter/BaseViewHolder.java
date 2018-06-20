package com.xx.baseuilibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * BaseViewHolder
 * (๑• . •๑)
 * 类描述:普通的BaseViewHolder
 * Created by LeiXiaoXing on 2017/3/17 14:12
 */

public abstract class BaseViewHolder<E> extends RecyclerView.ViewHolder {

    protected BaseViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    /**
     * 初始化视图
     *
     * @param itemView 子视图
     */
    protected void initView(View itemView) {
    }

    /**
     * 绑定数据
     *
     * @param entity 实体对象
     */
    protected abstract void bindView(int position, E entity);
}
