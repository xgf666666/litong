package com.weibiaogan.litong.entity;

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
public class WorkListBean {

    /**
     * user_id : 1
     * gr_id : 2
     * user_phone : 13682873453
     * user_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
     * distance : 12318305
     */

    private int user_id;
    private int gr_id;
    private String user_phone;
    private String user_img;
    private int distance;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGr_id() {
        return gr_id;
    }

    public void setGr_id(int gr_id) {
        this.gr_id = gr_id;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
