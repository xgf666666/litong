package com.xx.baseuilibrary.mvp.contract;

import com.xx.baseuilibrary.mvp.lcec.BaseMvpLcecView;
import com.xx.baseutilslibrary.network.rx.XxBaseHttpObserver;

import java.util.List;

/**
 * BaseMvpRecycleViewContract
 * (。・∀・)ノ
 * Describe：
 * Created by 雷小星🍀 on 2017/8/4 9:47.
 */

public interface BaseMvpRecycleViewContract {

    interface Model<E> {
        /**
         * 加载数据
         *
         * @param page         页数
         * @param httpObserver 观察者
         */
        void loadData(int page, XxBaseHttpObserver<List<E>> httpObserver);
    }

    interface View<E> extends BaseMvpLcecView<List<E>> {
        /**
         * 加载更多数据
         *
         * @param entities 数据
         */
        void upDateAdd(List<E> entities);
    }

    interface Presenter {
        /**
         * 加载数据
         *
         * @param refresh 是否刷新
         */
        void loadData(boolean refresh);
    }
}
