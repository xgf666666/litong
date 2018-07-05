package com.weibiaogan.litong.widget;

import android.app.Activity;
import android.text.TextUtils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weibiaogan.litong.App;
import com.weibiaogan.litong.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * author: Hanson
 * date:   2017/6/7
 * describe:
 */

public class MyAddressPicker {
    private final Activity mActvity;
    private final OnAddressPickListener mListener;
    private static ArrayList<Province> mProvinceList;
    private Province mProvince = new Province();
    private City mCity = new City();
    private County mCounty = new County();

    static {
        initializeProvinceData();
    }

    public MyAddressPicker(Activity activity, OnAddressPickListener listener) {
        mActvity = activity;
        mListener = listener;
    }

    public void showAddressPicker(Province prov, City ct, County county, boolean showCounty) {
        try {
            String province = "", city = "", district = "";
            if (prov != null && !TextUtils.isEmpty(prov.getAreaId())) {
                province = prov.getName();
            }
            if (ct != null && !TextUtils.isEmpty(ct.getAreaId())) {
                city = ct.getName();
            }
            if (county != null && !TextUtils.isEmpty(county.getAreaId())) {
                district = county.getName();
            }

            AddressPicker picker = new AddressPicker(mActvity, mProvinceList);
            picker.setShadowVisible(false);
            picker.setTextColor(mActvity.getResources().getColor(R.color.colorText000000),
                    mActvity.getResources().getColor(R.color.colorDeepGray));
            picker.setCancelTextColor(mActvity.getResources().getColor(R.color.colorText000000));
            picker.setSubmitTextColor(mActvity.getResources().getColor(R.color.colorText000000));
            picker.setDividerColor(mActvity.getResources().getColor(R.color.colorText999999));
            picker.setTopLineColor(mActvity.getResources().getColor(R.color.colorText999999));
            picker.setHideCounty(!showCounty);
            picker.setSelectedItem(province, city, district);
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    mProvince = province;
                    mCity = city;
                    mCounty = county;
                    if (mListener != null) {
                        mListener.onAddressPicked(province, city, county);
                    }
                }
            });
            picker.show();
        } catch (Exception e) {

        }
    }

    public void showAddressPicker(String province, String city, String county) {
        convertAddressFromName(province, city, county);
        showAddressPicker(mProvince, mCity, mCounty, true);
    }

    public void showAddressPicker(String province, String city) {
        convertAddressFromName(province, city, null);
        showAddressPicker(mProvince, mCity, null, false);
    }

    private static void initializeProvinceData() {
        try {
            if (mProvinceList == null) {
                String json = ConvertUtils.toString(App.Companion.getInstance().getAssets().open("city.json"));
                mProvinceList = new Gson().fromJson(json, new TypeToken<List<Province>>() {
                }.getType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Province getSelectedProvice() {
        return mProvince;
    }


    public City getSelectedCity() {
        return mCity;
    }

    public String getFullAreaName() {
        StringBuilder sb = new StringBuilder();
        if (mProvince != null) {
            sb.append(TextUtils.isEmpty(mProvince.getName()) ? "" : mProvince.getName());
        }
        if (mCity != null && mProvince != null) {
            if (mCity.getAreaId() != null && !TextUtils.equals(mCity.getAreaId(), mProvince.getAreaId())) {
                sb.append(TextUtils.isEmpty(mCity.getName()) ? "" : mCity.getName());
            }
        }
        if (mCounty != null && mCity != null) {
            if (mCounty.getAreaId() != null && !TextUtils.equals(mCounty.getAreaId(), mCity.getAreaId())) {
                sb.append(TextUtils.isEmpty(mCounty.getName()) ? "" : mCounty.getName());
            }
        }
        return sb.toString();
    }

    public void convertAddressFromCode(String provCode, String cityCode, String countyCode) {
        convertAddressFromCode(provCode, cityCode, countyCode, mProvince, mCity, mCounty);
    }

    public void convertAddressFromName(String provCode, String cityCode, String countyCode) {
        convertAddressFromName(provCode, cityCode, countyCode, mProvince, mCity, mCounty);
    }

    public static String getAddressFromCode(String provCode, String cityCode, String countyCode) {
        Province province = new Province();
        City city = new City();
        County county = new County();
        convertAddressFromCode(provCode, cityCode, countyCode, province, city, county);

        StringBuilder sb = new StringBuilder();
        sb.append(TextUtils.isEmpty(province.getName()) ? "" : province.getName());
        sb.append(" ").append(TextUtils.isEmpty(city.getName()) ? "" : city.getName());
        sb.append(" ").append(TextUtils.isEmpty(county.getName()) ? "" : county.getName());

        return sb.toString();
    }

    public static void convertAddressFromCode(String provCode, String cityCode, String countyCode,
                                               Province outProvince,  City outCity,  County outCounty) {
        if (!TextUtils.isEmpty(provCode)) {
            for (Province item : mProvinceList) {
                if (item.getAreaId().equals(provCode)) {
                    outProvince.setAreaId(item.getAreaId());
                    outProvince.setAreaName(item.getAreaName());
                    outProvince.setCities(item.getCities());
                    break;
                }
            }
        }
        if (!TextUtils.isEmpty(cityCode) && outProvince != null) {
            for (City item : outProvince.getCities()) {
                if (item.getAreaId().equals(cityCode)) {
                    outCity.setAreaId(item.getAreaId());
                    outCity.setAreaName(item.getAreaName());
                    outCity.setProvinceId(item.getProvinceId());
                    outCity.setCounties(item.getCounties());
                    break;
                }
            }
        }
        if (!TextUtils.isEmpty(countyCode) && outProvince != null && outCity != null) {
            for (County item : outCity.getCounties()) {
                if (item.getAreaId().equals(countyCode)) {
                    outCounty.setAreaId(item.getAreaId());
                    outCounty.setAreaName(item.getAreaName());
                    outCounty.setCityId(item.getAreaId());
                    break;
                }
            }
        }
    }

    public static void convertAddressFromName(String province, String city, String county,
                                               Province outProvince,  City outCity,  County outCounty) {
        if (!TextUtils.isEmpty(province) && outProvince != null) {
            for (Province item : mProvinceList) {
                if (item.getName().equals(province)) {
                    outProvince.setAreaId(item.getAreaId());
                    outProvince.setAreaName(item.getAreaName());
                    outProvince.setCities(item.getCities());
                    break;
                }
            }
        }
        if (!TextUtils.isEmpty(city) && outProvince != null && outCity != null) {
            for (City item : outProvince.getCities()) {
                if (item.getName().equals(city)) {
                    outCity.setAreaId(item.getAreaId());
                    outCity.setAreaName(item.getAreaName());
                    outCity.setProvinceId(item.getProvinceId());
                    outCity.setCounties(item.getCounties());
                    break;
                }
            }
        }
        if (!TextUtils.isEmpty(county) && outProvince != null && outCity != null) {
            for (County item : outCity.getCounties()) {
                if (item.getName().equals(county)) {
                    outCounty.setAreaId(item.getAreaId());
                    outCounty.setAreaName(item.getAreaName());
                    outCounty.setCityId(item.getAreaId());
                    break;
                }
            }
        }
    }

    public static String getProviceFromCode(String provCode) {
        if (TextUtils.isEmpty(provCode)) {
            return "";
        }

        String province = null;
        for (Province item : mProvinceList) {
            if (provCode.equals(item.getAreaId())) {
                province = item.getAreaName();
                break;
            }
        }

        return province;
    }


    public interface OnAddressPickListener {
        void onAddressPicked(Province province, City city, County county);
    }
}
