package com.xx.baseuilibrary

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import com.xx.baseutilslibrary.common.XxResourceUtil

/**
 * BaseActivity
 * (。・∀・)ノ
 * Describe：封装AppCompatActivity一级基类
 * Created by 雷小星🍀 on 2017/10/30 15:21.
 */

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var mContext: Context
    protected lateinit var mPermissionsManager : PermissionsManager


    /**
     * 获取布局资源文件id
     *
     * @return 布局资源文件id
     */
    protected abstract fun getActivityLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        beforeSetContentView()
        setContentView(getActivityLayoutId())
        mPermissionsManager= PermissionsManager(this)
        mPermissionsManager.permissions= returnPermissionArr()
        mPermissionsManager.setPermissionCallback(MyOnPermissionsCallback())
        afterSetContentView()

    }



    /**
     * 在设置ContenView之前执行的操作
     * 需要时复写
     */
    fun beforeSetContentView() {}


    /**
     * 在设置ContenView之后执行的操作
     * 需要时复写
     */
    protected open fun afterSetContentView() {
        initData()
        initEvent()

        //初始化返回按钮
        val id = XxResourceUtil.getId(mContext, "ib_back")
        val ibBack = findViewById<ImageButton>(id)
        ibBack?.setOnClickListener {
            finish()
        }
    }

    /**
     * 初始化数据状态
     */
    protected abstract fun initData()


    /**
     * 初始化事件
     */
    protected abstract fun initEvent()


    /**
     * 权限申请结果回调
     */
    private inner class MyOnPermissionsCallback : PermissionsManager.OnPermissionsCallback {
        override fun hasPermissions() {
            requestPermissionsSuccess()
        }

        override fun noPermissions() {
            requestPermissionsFailed()
        }
    }

    /**
     * 有权限
     */
    open fun requestPermissionsSuccess() {

    }

    /**
     * 无权限
     */
    open fun requestPermissionsFailed() {

    }

    /**
     * 子类复写此方法返回需要申请的权限
     *
     * @return
     */
    open fun returnPermissionArr(): Array<out String>? {
        return arrayOf<String>()
    }


}
