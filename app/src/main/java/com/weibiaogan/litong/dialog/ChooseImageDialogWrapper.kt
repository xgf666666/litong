package com.weibiaogan.litong.dialog

import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.weibiaogan.litong.R

import com.xx.baseutilslibrary.common.ImageChooseHelper

/**
 * ChooseImageDialogWrapper
 * 沉迷学习不能自拔
 * Describe：
 * Created by 雷小星🍀 on 2018/3/16 15:46.
 */

class ChooseImageDialogWrapper(imageChooseHelper: ImageChooseHelper) {

    private var chooseImageDialog: AlertDialog? = null
    /**
     * 初始化默认图片选择对话框
     */
    private fun initChooseImageDialog(imageChooseHelper: ImageChooseHelper) {
        if (chooseImageDialog == null) {
            val contentView = LayoutInflater.from(imageChooseHelper.context).inflate(
                    R.layout.dialog_chooseimage, null)
            chooseImageDialog = AlertDialog.Builder(imageChooseHelper.context, R.style.bottom_to_top_dialog_style)
                    .setView(contentView)
                    .create()

            val onClickListener = View.OnClickListener { v ->
                when (v.id) {
                    R.id.btn_photo -> imageChooseHelper.startCamearPic()
                    R.id.btn_album -> imageChooseHelper.startImageChoose()
                    R.id.btn_cancel -> dismissChooseImage()
                }
                dismissChooseImage()
            }

            contentView.findViewById<View>(R.id.btn_photo).setOnClickListener(onClickListener)
            contentView.findViewById<View>(R.id.btn_album).setOnClickListener(onClickListener)
            contentView.findViewById<View>(R.id.btn_cancel).setOnClickListener(onClickListener)

            val window = chooseImageDialog!!.window
            if (window != null) {
                window.setGravity(Gravity.BOTTOM)//设置窗口位于底部
                window.getDecorView().setPadding(0, 0, 0, 0)
                val lp = window.getAttributes()
                lp.width = WindowManager.LayoutParams.MATCH_PARENT
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                window.setAttributes(lp)
            }
        }
    }

    /**
     * 显示选择图片的对话框
     */
    fun showChooseImage() {
        chooseImageDialog?.show()
    }

    /**
     * 隐藏对话框
     */
    fun dismissChooseImage() {
        if (chooseImageDialog != null && chooseImageDialog!!.isShowing()) {
            chooseImageDialog?.dismiss()
        }

    }

    init {
        initChooseImageDialog(imageChooseHelper)
    }
}
