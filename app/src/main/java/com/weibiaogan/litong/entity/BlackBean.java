package com.weibiaogan.litong.entity;

/**
 * author: HuaiXianZhong
 * date: 2018/7/13
 * describe:  黑名单
 */
public class BlackBean {

    /**
     * user_id : 3918
     * worker_service : 电焊工能手
     * gr_id : 1
     * user_phone : 130****0850
     * nickname : 我就是我不一样的烟火
     * user_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
     * distance : 12285028
     */

    private int user_id;
    private String worker_service;
    private int gr_id;
    private String user_phone;
    private String nickname;
    private String user_img;
    private int distance;
    private String worker_address;
    private int id;

    public void setId(int mId) {
        id = mId;
    }

    public int getId() {
        return id;
    }

    public void setWorker_address(String mWorker_address) {
        worker_address = mWorker_address;
    }

    public String getWorker_address() {
        return worker_address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getWorker_service() {
        return worker_service;
    }

    public void setWorker_service(String worker_service) {
        this.worker_service = worker_service;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
