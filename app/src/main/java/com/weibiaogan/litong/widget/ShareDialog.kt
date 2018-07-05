package com.weibiaogan.litong.widget

import android.content.Context
import android.view.View
import com.flyco.dialog.widget.base.BottomBaseDialog
import com.weibiaogan.litong.R
import com.weibiaogan.litong.entity.ShareBean
import kotlinx.android.synthetic.main.dialog_share.*

/**
 * author: Gubr
 * date: 2018/5/10
 * describe:分享dialgo
 */
class ShareDialog(context: Context) : BottomBaseDialog<ShareDialog>(context),View.OnClickListener {
    var onClickListener:((v:View?)->Unit)?=null


    override fun onClick(v: View?) {
        onClickListener?.invoke(v)
    }

    private var mData: ShareBean?=null

    override fun onCreateView(): View {
        val view = View.inflate(context, R.layout.dialog_share, null)
        return view
    }

    override fun setUiBeforShow() {
        tv_share_circle.setOnClickListener(this)
        tv_share_qq.setOnClickListener(this)
        tv_share_wechat.setOnClickListener(this)
        tv_share_wechat.setOnClickListener(this)
        tv_cancel.setOnClickListener { dismiss() }
    }


    public fun setData(data: ShareBean):ShareDialog{
        mData=data
        return this
    }

}