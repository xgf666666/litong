package com.weibiaogan.litong.entity;

/**
 * author: HuaiXianZhong
 * date: 2018/7/10
 * describe:
 */
public class ProjectBean {

    /**
     * pt_imgs : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
     * pt_id : 2
     * pt_name : 广州二运集团有限公司: 西洲中公交立体充电桩停车场项目
     * end_time : 2018.08.08
     * all_price : 600.00
     * distance : 12318305
     */

    private String pt_imgs;
    private int pt_id;
    private String pt_name;
    private String end_time;
    private String all_price;
    private int distance;

    public String getPt_imgs() {
        return pt_imgs;
    }

    public void setPt_imgs(String pt_imgs) {
        this.pt_imgs = pt_imgs;
    }

    public int getPt_id() {
        return pt_id;
    }

    public void setPt_id(int pt_id) {
        this.pt_id = pt_id;
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
