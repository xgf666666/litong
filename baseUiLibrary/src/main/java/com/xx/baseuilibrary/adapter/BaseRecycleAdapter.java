package com.xx.baseuilibrary.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xx.baseuilibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseRecycleAdapter
 * (๑• . •๑)
 * 类描述:普通RecycleView的普通BaseAdapter
 * 支持多类型item，支持加载更多,通过{{@link BaseRecycleAdapter#setLoadMoreEnable}开启}
 * 支持Empty布局
 * simple
 * Created by LeiXiaoXing on 2017/3/17 14:26
 */

public abstract class BaseRecycleAdapter<E, VH extends BaseViewHolder<E>> extends RecyclerView.Adapter<BaseViewHolder<E>> {
    /**
     * Item类型:  加载更多
     */
    private static final int ITEM_TYPE_LOAD_MORE = -1;
    /**
     * Item类型： Empty
     */
    private static final int ITEM_TYPE_EMPTY = -2;
    private int limit = 20;
    private List<E> mEList;
    private Context mContext;
    private OnItemClickListener<E> mListener;
    /**
     * 是否支付加载更多,默认不开启
     */
    private boolean canLoadMore;
    /**
     * 是否加载完全部，默认可以加载更多
     */
    private boolean isLoadAll;
    private View emptyView;//空布局视图
    private int emptyViewLayoutId;//空布局布局id

    protected BaseRecycleAdapter() {
    }

    /**
     * 获取每页限制数据条数
     *
     * @return
     */
    public int getLimit() {
        return limit;
    }

    /**
     * 设置每页数据条数
     *
     * @param limit 限制数据条数
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * 是否支持加载更多
     *
     * @param enable
     */
    public void setLoadMoreEnable(boolean enable) {
        this.canLoadMore = enable;
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    public List<E> getEList() {
        return mEList;
    }

    /**
     * 设置空布局
     *
     * @param emptyView 空布局
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    /**
     * 设置空布局布局文件id
     *
     * @param emptyViewLayoutId 空布局布局文件id
     */
    public void setEmptyView(@LayoutRes int emptyViewLayoutId) {
        this.emptyViewLayoutId = emptyViewLayoutId;
    }


    @Override
    public BaseViewHolder<E> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        int measuredWidth = parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingLeft();
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            //返回加载更多类型 ViewHolder
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_load_and_all, parent, false);
            return new LoadMoreViewHolder(view);
        } else if (viewType == ITEM_TYPE_EMPTY) {
            //返回空布局类型 ViewHolder
            if (emptyView != null) {
                //传入空布局
                return new EmptyViewHolder(emptyView);
            } else if (emptyViewLayoutId != 0) {
                //传入空布局id
                View view = LayoutInflater.from(mContext).inflate(emptyViewLayoutId, parent, false);
                return new EmptyViewHolder(view);
            } else {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_empty, parent, false);
                return new EmptyViewHolder(view);
            }

        } else {
            View view = LayoutInflater.from(mContext).inflate(getItemLayout(viewType), parent, false);
            setItemWidth(false, view, measuredWidth);
            return createViewHolder(view, viewType);
        }
    }

    public void setItemWidth(boolean b, View view, int measuredWidth) {
        if (b) {
            view.getLayoutParams().width = (measuredWidth - view.getPaddingLeft() - view.getPaddingRight()) / 3;
        }
    }


    @Override
    public void onBindViewHolder(final BaseViewHolder<E> holder, int position) {
        final int adapterPosition = holder.getAdapterPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    if (mEList.get(adapterPosition) == null) {
                        return;
                    }
                    mListener.onItemClick(adapterPosition, mEList.get(adapterPosition));
                }
            }
        });
        holder.bindView(adapterPosition, mEList.get(adapterPosition));

    }

    @Override
    public int getItemViewType(int position) {
        if (canLoadMore && position > limit - 1 && position == mEList.size() - 1) {
            //如果开启加载更多，显示加载中
            return ITEM_TYPE_LOAD_MORE;
        } else if (mEList.get(0) == null) {
            //如果无内容，显示空布局
            return ITEM_TYPE_EMPTY;
        }
        return getCustomItemViewType(position);
    }

    /**
     * 创建自定义的item Type
     *
     * @param position item下标
     * @return 自定义item Type
     */
    protected int getCustomItemViewType(int position) {
        return 0;
    }

    /**
     * 创建ViewHolder
     *
     * @param view     item
     * @param viewType item视图类型
     * @return ViewHolder
     */
    protected abstract VH createViewHolder(View view, int viewType);

    /**
     * 获取子项布局文件id
     *
     * @return 子项布局文件id
     */
    protected abstract int getItemLayout(int viewType);

    /**
     * 刷新列表数据
     *
     * @param entitys 新的数据集合
     */
    public void upDate(List<E> entitys) {
        this.mEList = entitys;
        isLoadAll = false;//刷新列表,取消已加载完全部状态
        if (entitys != null && entitys.size() != 0) {
            //有内容，移除EmptyView
            mEList.remove(null);
        }
        notifyDataSetChanged();
    }

    /**
     * 追加数据并更新
     *
     * @param entitys 新的数据集合
     */
    public void upDateAdd(List<E> entitys) {

        if (this.mEList == null) {
            mEList = entitys;
            if (entitys != null && entitys.size() != 0) {
                //有内容，移除EmptyView
                mEList.remove(null);
            }
        } else if (entitys != null) {
            if (canLoadMore) {
                //k
                isLoadAll = entitys.size() == 0;
                if (entitys.size() != 0) {
                    //移除加载更多
                    isLoadAll = false;
                }
            }
            //移除空布局,移除加载更多
            mEList.remove(null);
            mEList.addAll(entitys);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (canLoadMore && mEList != null && mEList.size() >= limit) {
            //数据条数大于每页条数时显示加载更多
            //添加加载更多到尾部
            if (!mEList.contains(null)) {
                mEList.add(null);
            }
        } else if (mEList == null || mEList.size() == 0) {
            //无内容，添加EmptyView
            mEList = new ArrayList<>();
            mEList.add(null);
        }
        return mEList.size();
    }

    /**
     * 是否加载完成全部数据
     *
     * @return boolean
     */
    public boolean isLoadAll() {
        return isLoadAll;
    }

    public void setOnItemClickListener(OnItemClickListener<E> listener) {
        mListener = listener;
    }


    public interface OnItemClickListener<E> {
        void onItemClick(int position, E entity);
    }

    /**
     * 加载更多item以及加载完全部  ViewHolder
     */
    private class LoadMoreViewHolder extends BaseViewHolder<E> {

        private View viewLoadAll;
        private View viewLoading;

        LoadMoreViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            viewLoadAll = itemView.findViewById(R.id.view_load_all);
            viewLoading = itemView.findViewById(R.id.view_loading);
            itemView.setOnClickListener(null);
        }

        @Override
        protected void bindView(int position, E entity) {
            viewLoadAll.setVisibility(isLoadAll ? View.VISIBLE : View.GONE);
            viewLoading.setVisibility(isLoadAll ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * 空布局ViewHolder
     */
    private class EmptyViewHolder extends BaseViewHolder<E> {

        EmptyViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initView(View itemView) {
            itemView.setOnClickListener(null);
        }

        @Override
        protected void bindView(int position, E entity) {

        }
    }

}
