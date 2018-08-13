package com.weibiaogan.litong.entity;

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
public class SearchProjectBean {

    /**
     * pt_id : 11
     * pt_imgs : /uploads/20180704/15149dbf7fb5fabd5a6d264331361972.png
     * pt_name : 第一个项目
     * end_time : 2018.08.08
     * all_price : 600.00
     * distance : 12648823
     */

    private int pt_id;
    private String pt_imgs;
    private String pt_name;
    private String end_time;
    private String all_price;
    private int distance;
    private int type;//1项目，2工人，3店铺
    private String worker_address;
    private String worker_service;
    private String user_phone;
    private String st_address;
    private String st_img;
    private String user_img;
    private String nickname;
    private String st_name;
    private int st_id;
    private int user_id;
    private String user_sex;
    private String st_type;

    public String getSt_type() {
        return st_type;
    }

    public void setSt_type(String st_type) {
        this.st_type = st_type;
    }

    public String getSt_name() {
        return st_name;
    }

    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }

    public int getSt_id() {
        return st_id;
    }

    public void setSt_id(int  st_id) {
        this.st_id = st_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWorker_address() {
        return worker_address;
    }

    public void setWorker_address(String worker_address) {
        this.worker_address = worker_address;
    }

    public String getWorker_service() {
        return worker_service;
    }

    public void setWorker_service(String worker_service) {
        this.worker_service = worker_service;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getSt_address() {
        return st_address;
    }

    public void setSt_address(String st_address) {
        this.st_address = st_address;
    }

    public String getSt_img() {
        return st_img;
    }

    public void setSt_img(String st_img) {
        this.st_img = st_img;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPt_id() {
        return pt_id;
    }

    public void setPt_id(int pt_id) {
        this.pt_id = pt_id;
    }

    public String getPt_imgs() {
        return pt_imgs;
    }

    public void setPt_imgs(String pt_imgs) {
        this.pt_imgs = pt_imgs;
    }

    public String getPt_name() {
        return pt_name;
    }

    public void setPt_name(String pt_name) {
        this.pt_name = pt_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getAll_price() {
        return all_price;
    }

    public void setAll_price(String all_price) {
        this.all_price = all_price;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
