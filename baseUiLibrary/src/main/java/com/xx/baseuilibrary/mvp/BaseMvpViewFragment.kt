package com.xx.baseuilibrary.mvp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.xx.baseuilibrary.BaseFragment
import com.xx.baseuilibrary.R


/**
 * BaseMvpViewFragment
 * (。・∀・)ノ
 * Describe：
 * Created by 雷小星🍀 on 2017/11/2 18:22.
 */
abstract class BaseMvpViewFragment : BaseFragment() {
    /**
     * 显示Toast信息
     *
     * @param msg 消息信息
     */

    fun showToast(msg: String?) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        ToastUtils.setBgColor(ContextCompat.getColor(mContext, R.color.colorBlack))
        ToastUtils.setMsgColor(ContextCompat.getColor(mContext, R.color.colorWhite))
        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
        ToastUtils.showShort(msg)
    }



    final fun onError(msg: String?) {
        if (onInternalError(msg ?: "")) {
            showToast(msg)
        }
    }


    open fun onInternalError(msg: String): Boolean {
        if ("444".equals(msg)) {
            val intent = Intent()
            intent.action = "login_action"
            intent.addCategory("login_category")
            startActivity(intent)
            return true;
        }
        return false;
    }

    /**
     * 启动一个Activity
     *
     * @param clz Activity类
     */
    fun startActivity(clz: Class<out Activity>) {
        startActivity(Intent(mContext, clz))
    }

    /**
     * 结束Activity
     */
    fun finishActivity() {
        activity?.finish()
    }

    /**
     * 启动一个Activity然后结束自己
     *
     * @param clz Activity类
     */
    fun startActivityThenFinishSelf(clz: Class<out Activity>) {
        startActivity(Intent(mContext, clz))
        activity!!.finish()
    }


    private var alertDialog: AlertDialog? = null

    private lateinit var dialogMsgTextView: TextView
    /**
     * 显示加载进度框
     */
    fun showLoadingDialog(vararg msg: String?) {
        activity?.runOnUiThread {
        if (alertDialog == null) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress, null)
            dialogMsgTextView = view.findViewById(R.id.tv_msg)
            alertDialog = AlertDialog
                    .Builder(mContext)
                    .setView(view)
                    .create()
            alertDialog!!.setCanceledOnTouchOutside(false)
            if (alertDialog!!.window != null) {
                alertDialog!!.window!!.setBackgroundDrawable(BitmapDrawable())
            }
        }


            dialogMsgTextView.text = if (msg.size == 0) "" else msg[0]
            alertDialog?.show()
        }
    }

    /**
     * 隐藏加载进度框
     */
    fun dismissLoadingDialog() {
        if (alertDialog != null) {
            activity!!.runOnUiThread {
                alertDialog!!.dismiss()
            }
        }
    }

}