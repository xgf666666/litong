package com.weibiaogan.litong.entity;

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
public class StoreListBean {


    /**
     * st_id : 4
     * st_name : 3333333333333333333
     * st_address : 中国 广东 广州市白云区 太和镇沙亭大塘西17巷9号三楼
     * st_img : /uploads/20180704/f6d5e5c104552085effc47b25fc64fb8.png
     * st_type : 建材、装饰材料批发;环保技术开发服务;环保技术推广
     * distance : 71579
     */

    private int st_id;
    private String st_name;
    private String st_address;
    private String st_img;
    private String st_type;
    private int distance;

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

    public String getSt_type() {
        return st_type;
    }

    public void setSt_type(String st_type) {
        this.st_type = st_type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
