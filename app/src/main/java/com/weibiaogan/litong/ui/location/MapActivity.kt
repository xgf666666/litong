package com.weibiaogan.litong.ui.location

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.util.Log
import android.view.MotionEvent
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.*
import com.weibiaogan.litong.R
import kotlinx.android.synthetic.main.activity_map_location.*
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils
import com.weibiaogan.litong.MainActivity
import com.weibiaogan.litong.common.Constants
import com.weibiaogan.litong.utils.OpenNavigationUtils
import com.weibiaogan.litong.widget.ActionSheetDialog
import java.util.ArrayList
import kotlin.math.ln


/**
 * author: HuaiXianZhong
 * date: 2018/7/12
 * describe:
 */
class MapActivity : Activity(), AMap.OnMyLocationChangeListener {

    private var needPermissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,/*
                                          Manifest.permission.WRITE_EXTERNAL_STORAGE,*/
            /*Manifest.permission.READ_EXTERNAL_STORAGE,*/
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CHANGE_WIFI_STATE)

    var mAmap : AMap? = null

    var isClick = false

    var mPoint : Point? = null

    var geocodeSearch : GeocodeSearch? = null

    var result : RegeocodeResult? = null

    var type = 0

    var lat = ""
    var lng = ""
    var address = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)
        type = intent.getIntExtra("type_location", 0)
        if(type != 0){
            lat = intent.getStringExtra("lat_location")
            lng = intent.getStringExtra("lng_location")
            Log.i("map_location", "lat::$lat::lng:$lng")
            var address = intent.getStringExtra("address_location")
            tv_store_list_title.text = "店铺详情-导航"
            tv__my_location.text = "目的地"
            tv_location.text = address
            btn_location_sure.text = "导航"
        }
        location_map_view.onCreate(savedInstanceState)
        if (mAmap == null) mAmap = location_map_view.map
        if (!PermissionUtils.isGranted(needPermissions[0],needPermissions[1],needPermissions[2],needPermissions[3])){

            PermissionUtils.permission(needPermissions[0],needPermissions[1],needPermissions[2],needPermissions[3]).callback(object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    init()
                }

                override fun onDenied() {
                    //ToastUtils.showShort("没有相关权限，无法正常定位,请去设置界面允许定位")
                }

            }).rationale { it.again(true) }.request()
        }else{
            init()
        }

        initEvent()
    }

    fun init(){
        if (type != 0){
            var latLng = LatLng(lat.toDouble(), lng.toDouble())
            maker(latLng,"目的地")
            var cameraUpdate = CameraUpdateFactory.newCameraPosition(CameraPosition(latLng, 17f, 30f, 0f))
            mAmap?.moveCamera(cameraUpdate)
            return
        }
        mAmap?.moveCamera(CameraUpdateFactory.zoomTo(17f))
        val myLocationStyle: MyLocationStyle
        myLocationStyle = MyLocationStyle()//初始化定位蓝点样式类
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        mAmap?.setMyLocationStyle(myLocationStyle)//设置定位蓝点的Style
        mAmap?.getUiSettings()?.setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        mAmap?.setMyLocationEnabled(true)// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAmap?.setOnMyLocationChangeListener(this)

        mAmap?.setOnMapTouchListener(AMap.OnMapTouchListener { motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> isClick = true
                MotionEvent.ACTION_UP -> if (isClick) {
                    val rawX = motionEvent.x
                    val rawY = motionEvent.y
                    toGeoLocation(rawX.toInt(), rawY.toInt())

                }
                MotionEvent.ACTION_MOVE -> isClick = false
            }
        })

        geocodeSearch = GeocodeSearch(this)
        geocodeSearch?.setOnGeocodeSearchListener(object : GeocodeSearch.OnGeocodeSearchListener {
            override fun onRegeocodeSearched(regeocodeResult: RegeocodeResult, i: Int) {
                if (i == 1000){
                    result = regeocodeResult
                    var regeocodeAddress = regeocodeResult.regeocodeAddress
                    Log.i("Tag",regeocodeAddress.district+":::::"+regeocodeAddress.adCode+":::"+regeocodeAddress.pois)
                    tv_location.text = regeocodeAddress.formatAddress
                }else{
                    ToastUtils.showShort("error::$i")
                }
            }

            override fun onGeocodeSearched(geocodeResult: GeocodeResult, i: Int) {

            }
        })
    }

    fun initEvent(){
        ib_back.setOnClickListener { finish() }
        btn_location_sure.setOnClickListener {
            if (type != 0){
                showDialog()
            }else{
                if (result != null && result?.regeocodeAddress != null){
                    Constants.putLocation(lats!!,logs!!,result?.regeocodeAddress?.city)   //存储经纬度，城市名
                    var intent = Intent()
                    intent.putExtra("location_result", result?.regeocodeAddress?.pois!!.get(0).title)
                    intent.putExtra("location_address",result?.regeocodeAddress?.formatAddress)
                    intent.putExtra("location_lat",""+lats)
                    intent.putExtra("location_log",""+logs)
                    setResult(MainActivity.RESULT_CODE,intent)
                    finish()
                }else{
                    ToastUtils.showShort("无法定位")
                }
            }
        }
    }

    override fun onMyLocationChange(p0: Location?) {
        lats=p0?.latitude
        logs=p0?.longitude
        getcoderSearch(LatLonPoint(p0?.latitude!!, p0?.longitude!!))
    }

    var lats:Double?=0.0//维度
    var logs:Double?=0.0//经度
    private fun toGeoLocation(x: Int, y: Int) {

        //            x = Integer.parseInt(xView.getText().toString().trim());
        //            y = Integer.parseInt(yView.getText().toString().trim());
        mPoint = Point(x, y)
        var mLatlng = mAmap?.getProjection()?.fromScreenLocation(mPoint)
        if (mLatlng != null) {
            maker(mLatlng,"你所点击的位置")
            getcoderSearch(LatLonPoint(mLatlng.latitude, mLatlng.longitude))
            lats=mLatlng.latitude
            logs=mLatlng.longitude
        }


    }

    var markerOptions : MarkerOptions? = null
    private fun maker(latLng: LatLng,title:String) {
        // 动画效果
        val giflist = ArrayList<BitmapDescriptor>()
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        //        giflist.add(BitmapDescriptorFactory
        //                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
        //        giflist.add(BitmapDescriptorFactory
        //                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        if (markerOptions == null) {
            markerOptions = MarkerOptions()
        }


        markerOptions?.anchor(0.5f, 0.5f)
                ?.position(latLng)?.title(title)?.icons(giflist)
                ?.draggable(true)?.period(10)
        mAmap?.clear()

        mAmap?.addMarker(markerOptions)
    }

    /**
     * 逆地理编码
     */
    private fun getcoderSearch(mPoint: LatLonPoint) {
        try {

            val query = RegeocodeQuery(mPoint, 200f, GeocodeSearch.AMAP)
            geocodeSearch?.getFromLocationAsyn(query)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onResume() {
        super.onResume()
        location_map_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        location_map_view.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        location_map_view.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        location_map_view.onDestroy()
    }

    fun showDialog(){

        var items = Array(2){""}
        items[0] = "百度地图"
        items[1] = "高德地图"
        var dialog = ActionSheetDialog(this,items ,null)
        dialog.cancelText("不去了")
        dialog.isTitleShow(false)
        dialog.show()
        dialog.setCancelTextOnClicklistener {  }
        dialog.setOnOperItemClickL { parent, view, position, id ->
            var s = OpenNavigationUtils.getInstatllMapSet()
            if (s.isEmpty()){
                ToastUtils.showShort("请先安装"+items[position])
                dialog.dismiss()
            }else{
                for (i in s){
                    if (items[position] == i){
                        OpenNavigationUtils.openMap(i,lat, lng)
                    }
                }
                ToastUtils.showShort("请先安装"+items[position])
            }
        }


    }

}