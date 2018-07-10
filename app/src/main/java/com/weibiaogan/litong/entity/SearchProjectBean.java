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
