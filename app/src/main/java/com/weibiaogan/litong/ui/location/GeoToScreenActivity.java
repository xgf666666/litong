package com.weibiaogan.litong.ui.location;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.weibiaogan.litong.R;
import com.weibiaogan.litong.utils.AMapUtil;

import java.util.ArrayList;


public class GeoToScreenActivity extends Activity implements OnMapClickListener, OnClickListener {
    private static final String TAG = "GeoToScreenActivity";
    private AMap aMap;
    private MapView mapView;
    private EditText latView, lngView, xView, yView;
    private Button lnglat2pointBtn, point2LatlngBtn;
    private Point mPoint;
    private LatLng mLatlng;
    private int x, y;
    private float lat, lng;
    private MarkerOptions markerOptions;
    private GeocodeSearch geocodeSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location2screen_activity);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        //init();
        if (aMap == null) aMap = mapView.getMap();

    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
                @Override
                public void onTouch(MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            isClick=true;
                            break;
                        case MotionEvent.ACTION_UP:
                            if (isClick) {
                                float rawX = motionEvent.getX();
                                float rawY = motionEvent.getY();
                                toGeoLocation(((int) rawX), ((int) rawY));

                            }
                            break;
                        case MotionEvent.ACTION_MOVE:
                            isClick=false;
                            break;
                    }
                }
            });

            geocodeSearch = new GeocodeSearch(this);
            geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
                @Override
                public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                    RegeocodeResult regeocodeResult1 = regeocodeResult;
                }

                @Override
                public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                    GeocodeResult geocodeResult1 = geocodeResult;
                }
            });
        }
        latView = (EditText) findViewById(R.id.pointLat);
        lngView = (EditText) findViewById(R.id.pointLng);
        xView = (EditText) findViewById(R.id.pointX);
        yView = (EditText) findViewById(R.id.pointY);
        lnglat2pointBtn = (Button) findViewById(R.id.lnglat2pointbtn);
        point2LatlngBtn = (Button) findViewById(R.id.point2Latlngbtn);
        lnglat2pointBtn.setOnClickListener(this);
        point2LatlngBtn.setOnClickListener(this);
    }

    private boolean isClick=false;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.getRawX());
        return super.onTouchEvent(event);
    }

    private void setUpMap() {
        aMap.setOnMapClickListener(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapClick(LatLng arg0) {
        latView.setText(String.valueOf(arg0.latitude));
        lngView.setText(String.valueOf(arg0.longitude));



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnglat2pointbtn:
                toScreenLocation();
                break;
            case R.id.point2Latlngbtn:
//                toGeoLocation();
                break;
            default:
                break;
        }

    }

    private void maker(LatLng latLng){
        // 动画效果
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        giflist.add(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        giflist.add(BitmapDescriptorFactory
//                .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        if (markerOptions == null) {
            markerOptions = new MarkerOptions();
        }


        markerOptions.anchor(0.5f, 0.5f)
                .position(latLng).title("你点击的位置").icons(giflist)
                .draggable(true).period(10);
        aMap.clear();

        aMap.addMarker(markerOptions);
    }


    private void getcoderSearch(LatLonPoint mPoint){
        try {

            RegeocodeQuery query = new RegeocodeQuery(mPoint, 200, GeocodeSearch.AMAP);
            geocodeSearch.getFromLocationAsyn(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void toGeoLocation(int x,int y) {

//            x = Integer.parseInt(xView.getText().toString().trim());
//            y = Integer.parseInt(yView.getText().toString().trim());
            mPoint = new Point(x, y);
            mLatlng = aMap.getProjection().fromScreenLocation(mPoint);
            if (mLatlng != null) {
                maker(mLatlng);
                getcoderSearch(new LatLonPoint(mLatlng.latitude,mLatlng.longitude));
            }


    }

    private void toScreenLocation() {
        if (AMapUtil.IsEmptyOrNullString(latView.getText().toString()) ||
                AMapUtil.IsEmptyOrNullString(lngView.getText().toString())) {
            Toast.makeText(GeoToScreenActivity.this, "经纬度为空", Toast.LENGTH_SHORT).show();
        } else {
            lat = Float.parseFloat(latView.getText().toString().trim());
            lng = Float.parseFloat(lngView.getText().toString().trim());
            mLatlng = new LatLng(lat, lng);
            mPoint = aMap.getProjection().toScreenLocation(mLatlng);
            if (mPoint != null) {
                xView.setText(String.valueOf(mPoint.x));
                yView.setText(String.valueOf(mPoint.y));
            }
        }
    }

}
