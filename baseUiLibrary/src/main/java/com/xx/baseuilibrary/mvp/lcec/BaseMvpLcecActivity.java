package com.xx.baseuilibrary.mvp.lcec;

import android.support.annotation.NonNull;
import android.view.View;

import com.xx.baseuilibrary.mvp.BaseMvpPresenter;


/**
 * BaseMvpLcecActivity
 * (๑• . •๑)
 * 类描述:
 * Created by 雷小星🍀 on 2017/6/22 12:02
 */
public abstract class BaseMvpLcecActivity<ContentView extends View, Data, M,V extends BaseMvpLcecView<Data>, P extends BaseMvpPresenter<M,V>>
        extends BaseLcecActivity<ContentView, Data> {

    private P mPresenter;

    /**
     * 获取Presenter
     *
     * @return Presenter
     */
    @NonNull
    protected abstract P createPresenter();

    /**
     * 获取Presenter
     *
     * @return Presenter
     */
    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
            if (mPresenter == null) {
                throw new NullPointerException("在createPresenter()方法中返回了null");
            }
            mPresenter.attachView((V)this);
        }
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
    }
}
