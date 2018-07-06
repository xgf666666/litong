package com.weibiaogan.litong

import android.support.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.weibiaogan.litong.utils.LocationManger
import com.xx.baseutilslibrary.network.provider.JApiConfigProvider
import com.xx.baseutilslibrary.network.retrofit.Retrofit2Manager

/**
 * author: Gubr
 * date: 2018/5/9
 * describe:
 */
class App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Utils.init(this)
        Stetho.initializeWithDefaults(this)

        LocationManger.getInstance().init(this)


        UMInit()

        Retrofit2Manager.instance.apiConfigProvider = object : JApiConfigProvider {
            override fun getApiRelativePath(): String {
                return "/Api/"
            }

            override fun getDebugHost(): String {
//                return "http://192.168.0.150"
                return BuildConfig.DEV_DOMAIN
            }

            override fun getReleaseHost(): String {
                return BuildConfig.RELEASE_DOMAIN

            }

            override fun isDebug(): Boolean {
                return BuildConfig.IS_DEV
            }

        }
    }

    private fun UMInit() {
        //初始化友盟
        UMConfigure.init(this, "5af105248f4a9d6df300028a"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
        PlatformConfig.setWeixin("wxd08a9b205494c248", "35c0bcca128270bd9b7ec01812fc97fa")
        PlatformConfig.setSinaWeibo("4285643628", "0dcfdb9213e48fb0bfd8f7479f208bf8", "http:www.baidu.com")
        PlatformConfig.setQQZone("1106602798", "gDWIr2lY4PT4OIVa")
    }

    companion object {
        @JvmStatic
        private var sInstance: App? = null

        fun getInstance(): App? {
            return sInstance
        }
    }


}