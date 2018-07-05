package com.weibiaogan.litong.common

import com.blankj.utilcode.util.EncryptUtils

fun String.md5Salt():String =
        EncryptUtils.encryptMD5ToString(EncryptUtils.encryptMD5ToString(this + "mcjp")
                .toLowerCase()).toLowerCase()