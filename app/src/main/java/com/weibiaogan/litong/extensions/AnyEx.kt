package com.weibiaogan.litong.extensions

import com.weibiaogan.litong.App

/**
 * author: Gubr
 * date: 2018/5/28
 * describe:
 */
fun dp(dpId: Int): Int {
    return App.getInstance()?.resources?.getDimensionPixelOffset(dpId) ?: 0
}

