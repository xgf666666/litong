package com.weibiaogan.litong.extensions

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager

/**
 * author: Gubr
 * date: 2018/5/7
 * describe:
 */
fun ImageView.loadImag( url: String, tran: BitmapTransformation? = null, plach: Int = 0, error: Int = 0) {
    var urltemp=url
    if (url.length>1&&url[0].equals('/')) {
        urltemp=Retrofit2Manager.instance.apiConfigProvider?.debugHost+url
    }
    Glide.with(this.context).load(urltemp).also {
        if (tran != null) {
            it.transform(tran)
        }
        if (plach != 0) {
            it.placeholder(plach)
        }
        if (error != 0) {
            it.error(error)
        }
    }.into(this)
}


fun TextView.setHtmlText(content: String) {
    this.text = Html.fromHtml(content)
}

fun TextView.getTString()=this.text.toString().trim()



fun View.setOnPerCheckLoginClickListner(back:(v:View)->Unit){
    this.setOnClickListener {
        if (com.weibiaogan.litong.common.Constants.isLogin()) {
            back.invoke(it)
        }else{
            val intent = android.content.Intent(it.context, com.weibiaogan.litong.ui.login.LoginActivity::class.java)
            it.context.startActivity(intent)
        }
    }
}