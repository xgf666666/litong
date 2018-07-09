package com.weibiaogan.litong.entity;

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
public class StoreDetailBean {


    /**
     * st_id : 1
     * st_name : 你是风儿我是沙
     * st_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
     * st_type : 沙子
     * st_address : 富力中心
     * lat_long : 113.3212200000,23.1194300000
     * st_stat : 1
     * add_time : 1530088590
     * business_hours : 10:30-20:30
     * distance : 5254
     */

    private int st_id;
    private String st_name;
    private String st_img;
    private String st_type;
    private String st_address;
    private String lat_long;
    private int st_stat;
    private int add_time;
    private String business_hours;
    private int distance;
    private String st_phone;

    public void setSt_phone(String mSt_phone) {
        st_phone = mSt_phone;
    }

    public String getSt_phone() {
        return st_phone;
    }

    public int getSt_id() {
        return st_id;
    }

    public void setSt_id(int st_id) {
        this.st_id = st_id;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public String getSt_img() {
        return st_img;
    }

    public void setSt_img(String st_img) {
        this.st_img = st_img;
    }

    public String getSt_type() {
        return st_type;
    }

    public void setSt_type(String st_type) {
        this.st_type = st_type;
    }

    public String getSt_address() {
        return st_address;
    }

    public void setSt_address(String st_address) {
        this.st_address = st_address;
    }

    public String getLat_long() {
        return lat_long;
    }

    public void setLat_long(String lat_long) {
        this.lat_long = lat_long;
    }

    public int getSt_stat() {
        return st_stat;
    }

    public void setSt_stat(int st_stat) {
        this.st_stat = st_stat;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getBusiness_hours() {
        return business_hours;
    }

    public void setBusiness_hours(String business_hours) {
        this.business_hours = business_hours;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
