package com.weibiaogan.litong.ui.location

import android.app.Activity
import android.graphics.Point
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.model.*
import com.weibiaogan.litong.R
import kotlinx.android.synthetic.main.activity_map_location.*
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.geocoder.GeocodeResult
import com.amap.api.services.geocoder.GeocodeSearch
import com.amap.api.services.geocoder.RegeocodeQuery
import com.amap.api.services.geocoder.RegeocodeResult
import com.blankj.utilcode.util.ToastUtils
import java.util.ArrayList


/**
 * author: HuaiXianZhong
 * date: 2018/7/12
 * describe:
 */
class MapActivity : Activity(), AMap.OnMyLocationChangeListener {

    var mAmap : AMap? = null

    var isClick = false

    var mPoint : Point? = null

    var geocodeSearch : GeocodeSearch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_location)
        location_map_view.onCreate(savedInstanceState)

        if (mAmap == null) mAmap = location_map_view.map
        init()
    }

    fun init(){
        val myLocationStyle: MyLocationStyle
        myLocationStyle = MyLocationStyle()//初始化定位蓝点样式类
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        // 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        mAmap?.setMyLocationStyle(myLocationStyle)//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
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
                    var regeocodeAddress = regeocodeResult.regeocodeAddress
                    tv_location.text = regeocodeAddress.formatAddress
                }else{
                    ToastUtils.showShort("error::$i")
                }
            }

            override fun onGeocodeSearched(geocodeResult: GeocodeResult, i: Int) {

            }
        })
    }

    override fun onMyLocationChange(p0: Location?) {
        getcoderSearch(LatLonPoint(p0?.latitude!!, p0?.longitude!!))
    }

    private fun toGeoLocation(x: Int, y: Int) {

        //            x = Integer.parseInt(xView.getText().toString().trim());
        //            y = Integer.parseInt(yView.getText().toString().trim());
        mPoint = Point(x, y)
        var mLatlng = mAmap?.getProjection()?.fromScreenLocation(mPoint)
        if (mLatlng != null) {
            maker(mLatlng)
            getcoderSearch(LatLonPoint(mLatlng.latitude, mLatlng.longitude))
        }


    }

    var markerOptions : MarkerOptions? = null
    private fun maker(latLng: LatLng) {
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
                ?.position(latLng)?.title("你点击的位置")?.icons(giflist)
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
}