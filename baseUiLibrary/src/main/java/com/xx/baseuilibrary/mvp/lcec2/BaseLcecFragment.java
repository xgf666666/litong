package com.xx.baseuilibrary.mvp.lcec2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.view.View;

import com.xx.baseuilibrary.R;
import com.xx.baseuilibrary.mvp.BaseMvpViewFragment;
import com.xx.baseutilslibrary.common.XxResourceUtil;

import java.net.ConnectException;
import java.util.ArrayList;


/**
 * BaseFragment
 * (๑• . •๑)
 * 类描述:
 * Created by 雷小星🍀 on 2017/6/13 18:10
 */

public abstract class BaseLcecFragment<Data> extends BaseMvpViewFragment {
    private SparseArray<View> mSparseArray;


    @CallSuper
    @Override
    public void initView(View view) {
        initLcenView(view);
    }

    protected int getLoadingViewId() {
        return R.id.view_loading;
    }

    protected int getContentVIewId() {
        return R.id.view_content;
    }

    protected int getErrorViewId() {
        return R.id.view_error;
    }

    protected int getCheckNetViewId() {
        return R.id.view_check_net;
    }

    /**
     * 初始化Lcec
     */
    private void initLcenView(View view) {
//        viewLoading = findViewById(XxResourceUtil.getLayoutId(this, "view_loading"));
//        viewContent = (ContentView) findViewById(XxResourceUtil.getLayoutId(this, "view_content"));
//        viewError = findViewById(XxResourceUtil.getLayoutId(this, "view_error"));
//        viewCheckNet = findViewById(XxResourceUtil.getLayoutId(this, "view_check_net"));
        mSparseArray = new SparseArray<>();
        if (getLoadingViewId() != -1) {
            View tempView = view.findViewById(getLoadingViewId());
            mSparseArray.put(getLoadingViewId(), tempView);

        }

        if (getContentVIewId() != -1) {
            View tempView = view.findViewById(getContentVIewId());
            mSparseArray.put(getContentVIewId(), tempView);

        }

        if (getErrorViewId() != -1) {
            View tempView = view.findViewById(getErrorViewId());
            mSparseArray.put(getErrorViewId(), tempView);

        }

        if (getCheckNetViewId() != -1) {
            View tempView = view.findViewById(getCheckNetViewId());
            mSparseArray.put(getCheckNetViewId(), tempView);

        }


        final View tempView = mSparseArray.get(getErrorViewId());
        if (tempView != null) {
            tempView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLoading();
                    loadData(true);
                }
            });
        }

        final View clickNetButton = view.findViewById(R.id.tv_no_net);
        if (clickNetButton != null) {
            clickNetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 点击没有网络跳转到系统设置界面
                    showNoNetWorkDlg();
                }
            });
        }
        //初始显示加载中对话框
        showLoading();
    }





    public void addViewtoViews(View view) throws Exception {
        if (view.getId()!= View.NO_ID) {
            mSparseArray.put(view.getId(),view);
        }else{
            throw new Exception("View"+view.hashCode()+"must set view id");

        }
    }

    /**
     * 当判断当前手机没有网络时选择是否打开网络设置
     */
    public void showNoNetWorkDlg() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("当前无网络")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goSettings();
                    }
                }).setNegativeButton("知道了", null).show();
    }

    /**
     * 跳转到设置界面
     */
    public void goSettings() {
        // 跳转到系统的网络设置界面
        Intent intent;
        // 先判断当前系统版本
        if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
        }
        startActivity(intent);
    }

    /**
     * 获取内容视图
     *
     * @return 内容视图
     */
    protected <T extends View> T getContentView() {
        return (T) mSparseArray.get(getContentVIewId());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSparseArray != null) {
            mSparseArray.clear();
            mSparseArray = null;
        }
    }

    /**
     * 控制显示的View
     *
     * @param view 需要显示的View
     */
    protected void showView(View view) {
//        for (View v :
//                mViews) {
//            v.setVisibility(v == view ? View.VISIBLE : View.GONE);
//        }

        for (int i = 0; i < mSparseArray.size(); i++) {
            final int i1 = mSparseArray.keyAt(i);
            final View tempView = mSparseArray.get(i1);
            view.setVisibility(tempView == view ? View.VISIBLE : View.GONE);
        }

    }

    protected void showView(int viewId) {
        for (int i = 0; i < mSparseArray.size(); i++) {
            final int i1 = mSparseArray.keyAt(i);
            final View view = mSparseArray.get(i1);
            view.setVisibility(viewId == i1 ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 显示加载中布局
     */
    private void showLoading() {
        showView(getLoadingViewId());
    }

    /**
     * 显示内容布局
     */
    public void showContent() {
        showView(getContentVIewId());
    }

    /**
     * 显示错误布局
     */
    private void showError() {
        showView(getErrorViewId());
    }

    /**
     * 显示检查网络布局
     */
    private void showCheckNet() {
        showView(getCheckNetViewId());
    }

    /**
     * 显示错误信息
     *
     * @param throwable 错误
     * @param refresh   是否是刷新数据时
     */
    public void showError(Throwable throwable, boolean refresh) {
        if (throwable instanceof ConnectException || throwable.getMessage().equals("网络错误")) {
            //如果时网络出错的错误，显示检查检查网络
            showCheckNet();
        } else if (!refresh) {
            //如果在加载更多时出错，toast提示
            showContent();
            showToast(throwable.getMessage());
        } else {
            //其他错误显示通用错误页面
            showError();
        }

    }

    /**
     * 加载数据
     *
     * @param refresh 是否是刷新数据
     */
    protected abstract void loadData(boolean refresh);

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public abstract void setData(Data data);

}
