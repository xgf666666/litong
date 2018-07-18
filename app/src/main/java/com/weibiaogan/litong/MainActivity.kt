package com.weibiaogan.litong

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.View
import android.widget.RadioButton
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.PermissionUtils
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.common.Constants.KEY_INTENT_MAIN
import com.weibiaogan.litong.dialog.ChooseImageDialogWrapper
import com.weibiaogan.litong.ui.home.HomeFragment
import com.weibiaogan.litong.ui.location.GeoToScreenActivity
import com.weibiaogan.litong.ui.location.MapActivity
import com.weibiaogan.litong.ui.login.LoginActivity
import com.weibiaogan.litong.ui.orders.OrdersFragment
import com.weibiaogan.litong.ui.mine.MineFragment
import com.weibiaogan.litong.ui.project.Projectragment
import com.weibiaogan.litong.ui.search.SearchProjectActivity
import com.weibiaogan.litong.utils.LocationManger
import com.xx.baseuilibrary.mvp.BaseMvpViewActivity
import com.xx.baseutilslibrary.common.ImageChooseHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*


class MainActivity : BaseMvpViewActivity() {
    private lateinit var imageChooseHelper: ImageChooseHelper
    companion object {
        const val INDEX_HOME = 0
        const val INDEX_PERSON = 3//个人中心

        const val REQUEST_CODE = 0x11
        const val RESULT_CODE = 0x12
    }


    private var fragments: ArrayList<Fragment?>? = null

    override fun getActivityLayoutId(): Int = R.layout.activity_main

    public fun setRadio(){
        radio.check(3)
    }

    override fun initEvent() {
        radio.setOnCheckedChangeListener { _, checkedId ->
          /*  ll_search_area.visibility= if (checkedId==0) View.VISIBLE else View.GONE*/
//            iv_setup.visibility=if (checkedId==3)View.VISIBLE else View.GONE

            tb_cart_setup.visibility=if (checkedId==2) View.VISIBLE else View.GONE
            showFragment(checkedId)
            changeTitle(checkedId)
            supportFragmentManager.popBackStack()
        }



       /* ll_search_area.setOnClickListener {

           startActivity(SearchActivity::class.java)
        }*/

        iv_setup.setOnClickListener {

        }

        ll_home_search.setOnClickListener {  startActivity(Intent(mContext, SearchProjectActivity::class.java)) }
        tv_home_location.setOnClickListener { startActivityForResult(Intent(mContext, MapActivity::class.java),REQUEST_CODE) }

    }

    private fun changeTitle(i: Int) {
        when (i) {
            0 -> setTitleText("首页")
            1 -> setTitleText("发布项目")
            2 -> setTitleText("我要接单")
            3 -> setTitleText("我的")
        }
    }

    fun setTitleText(txt : String){
        if (txt.equals("首页")){
            ll_home_title.visibility = View.VISIBLE
        }else{
            ll_home_title.visibility = View.GONE
            tv_title.text = txt
        }
    }


    /**
     * 显示Fragment
     */
    public fun showFragment(checkedId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in fragments!!.indices) {
            val fragment = fragments!![i]
            if (i == checkedId) {
                //先检查Fragment是否创建
                if (fragment == null) {
                    when (i) {
                        0 -> {
                            fragments!![i] = HomeFragment()

                        }
                        1 -> fragments!![i] = Projectragment()
                        2 -> fragments!![i] = OrdersFragment()
                        3 -> fragments!![i] = MineFragment()
                    }
                    //添加到管理类
                    fragmentTransaction.add(R.id.container, fragments!![i])
                }
//                if (i==1||i==2||i==3){
//                    if (!Constants.isLogin()){
//                        startActivity(LoginActivity::class.java)
//                        return
//                    }
//                }
                fragmentTransaction.show(fragments!![i])
            } else {
                if (fragment != null) {
                    fragmentTransaction.hide(fragment)
                }
            }
        }
        fragmentTransaction.commit()
    }

    /**
     *初始化Fragments
     */
    private fun initFragments() {
        if (fragments == null) {
            //初始化只生成首页碎片
            fragments = arrayListOf(HomeFragment(), null, null, null)
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragments!![0])
                .show(fragments!![0])
                .commit()

    }

    var isExit: Boolean? = false//是否退出登录
    /**
     * 双击返回桌面
     */
    private fun back2exit() {
        if (isExit!!) {
            ActivityUtils.finishAllActivities(true)
        }
        isExit = true
        showToast("再按一次回到桌面")
        Timer().schedule(object : TimerTask() {
            override fun run() {
                isExit = false
            }

        }, 1500)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back2exit()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun initData() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = ContextCompat.getColor(mContext, R.color.colorWhite)
//        }

        //重置RadioGroup子项id
        for (i in 0 until radio!!.childCount!!) {
            radio!!.getChildAt(i)!!.id = i
        }
        initFragments()
        changeTitle(0)
        radio.check(INDEX_HOME)

        tv_home_location.text = Constants.getLocation()[2]
        initImageChooseHelper()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val intExtra = intent!!.getIntExtra(KEY_INTENT_MAIN, INDEX_HOME)
        (radio.getChildAt(intExtra) as RadioButton).isChecked = true
    }
    public fun showEditAvatarDialog() {
        //选图弹窗
        //请求相机和内存读取权限
        PermissionUtils.permission(Manifest.permission.CAMERA, Manifest.permission_group.STORAGE)
                .callback(object : PermissionUtils.SimpleCallback {
                    override fun onGranted() {
                        //被给予权限,调起选图弹窗
                        ChooseImageDialogWrapper(imageChooseHelper)
                                .showChooseImage()
                    }

                    override fun onDenied() {
                        //被拒绝
                        showToast("拒绝给予权限会导致该功能不能正常使用")
                    }
                })
                .rationale { shouldRequest -> shouldRequest.again(true) }
                .request()
    }
    //初始化调用相机和照片
    public fun initImageChooseHelper(){
        imageChooseHelper = ImageChooseHelper.Builder()
                .setUpActivity(mContext as Activity)
                .setAuthority("${BuildConfig.APPLICATION_ID}.fileprovider")//设置文件提供者
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/" + BuildConfig.APPLICATION_ID)//设置文件存储路径
                .isCrop(true)//开启裁剪
                .setCompressQuality(100)//压缩质量[1,100]
                .setSize(200, 200)//裁剪尺寸
                .setOnFinishChooseAndCropImageListener { bitmap, file ->

                    photoListener?.setPresenter(bitmap,file)
                }
                .create()
    }

    //创建一个接口回调数据给ProjectFragment
    var photoListener: PhotoListener?=null
    public fun setPhoto(photoListeners: PhotoListener){
        photoListener=photoListeners
    }
     interface  PhotoListener{
        fun setPresenter(bitmap: Bitmap,file:File)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE){
            tv_home_location.text = data?.getStringExtra("location_result")
        }else if (requestCode==2&& resultCode == RESULT_CODE){
            var projectragment=fragments!![1] as Projectragment
            var lots:String=data?.getStringExtra("location_log")?:""
            var lats:String=data?.getStringExtra("location_lat")?:""
            var address:String =data?.getStringExtra("location_address")?:""
            projectragment.setMap(lots ,lats,address )
        } else{
            imageChooseHelper.onActivityResult(requestCode, resultCode, data)
        }
    }

}
