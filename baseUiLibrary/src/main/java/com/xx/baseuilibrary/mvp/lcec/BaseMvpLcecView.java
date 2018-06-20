package com.xx.baseuilibrary.mvp.lcec;

import android.support.annotation.NonNull;

import com.xx.baseuilibrary.mvp.BaseMvpView;


/**
 * BaseMvpLcecView
 * (๑• . •๑)
 * 类描述:
 * Created by 雷小星🍀 on 2017/6/22 12:03
 */

public interface BaseMvpLcecView<Data> extends BaseMvpView {


    /**
     * 填充数据
     *
     * @param data 数据
     */
    void setData(Data data);

    /**
     * 显示错误信息
     *
     * @param throwable 错误
     * @param refresh   是否是刷新
     */
    void showError(@NonNull Throwable throwable, boolean refresh);
}
