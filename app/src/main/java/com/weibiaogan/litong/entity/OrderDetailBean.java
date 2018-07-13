package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: HuaiXianZhong
 * date: 2018/7/12
 * describe:  订单详情
 */
public class OrderDetailBean {

    /**
     * pt_id : 2
     * pt_name : 广州二运集团有限公司: 西洲中公交立体充电桩停车场项目
     * pt_describe : 第一个项目
     * end_time : 2018.08.08
     * pt_address : 西洲中公交立体充电桩停车场
     * area_id : 9
     * first_price : 20.00
     * second_price : 20.00
     * three_price : 60.00
     * pt_stat : 4
     * first_pay_time : 345
     * second_pay_time : 4353
     * three_pay_time : 3453
     * worker_comments : 0
     * boss_comments : 0
     * prepaid_price : 0.00
     * prepaid_stat : 0
     * prepaid_time : 0
     * add_time : 2018.06.25
     * pt_imgs : ["/uploads/20180704/ffb5f89bb13a1c946e9d04551005cc79.png","/uploads/20180704/ffb5f89bb13a1c946e9d04551005cc79.png"]
     * user_id : 3945
     * lat_long : 113.222801,23.169136
     * all_price : 600.00
     * pt_user_id : 1
     * hidden_phone : 0
     * worker_frist_time : 0
     * worker_second_time : 0
     * worker_three_time : 0
     * boss_prepaid_time : 0
     * boss_user : {"user_phone":"18888888888"}
     * add_blacklist : 0
     * show_pt_user : {"worker_user":"666","first_pay_time":"1970.01.01","second_pay_time":"1970.01.01","three_pay_time":"1970.01.01"}
     */

    private int pt_id;
    private String pt_name;
    private String pt_describe;
    private String end_time;
    private String pt_address;
    private int area_id;
    private String first_price;
    private String second_price;
    private String three_price;
    private int pt_stat;
    private int first_pay_time;
    private int second_pay_time;
    private int three_pay_time;
    private int worker_comments;
    private int boss_comments;
    private String prepaid_price;
    private int prepaid_stat;
    private int prepaid_time;
    private String add_time;
    private int user_id;
    private String lat_long;
    private String all_price;
    private int pt_user_id;
    private int hidden_phone;
    private int worker_frist_time;
    private int worker_second_time;
    private int worker_three_time;
    private int boss_prepaid_time;
    private BossUserBean boss_user;
    private int add_blacklist;
    private ShowPtUserBean show_pt_user;
    private List<String> pt_imgs;

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

    public String getPt_describe() {
        return pt_describe;
    }

    public void setPt_describe(String pt_describe) {
        this.pt_describe = pt_describe;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPt_address() {
        return pt_address;
    }

    public void setPt_address(String pt_address) {
        this.pt_address = pt_address;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getFirst_price() {
        return first_price;
    }

    public void setFirst_price(String first_price) {
        this.first_price = first_price;
    }

    public String getSecond_price() {
        return second_price;
    }

    public void setSecond_price(String second_price) {
        this.second_price = second_price;
    }

    public String getThree_price() {
        return three_price;
    }

    public void setThree_price(String three_price) {
        this.three_price = three_price;
    }

    public int getPt_stat() {
        return pt_stat;
    }

    public void setPt_stat(int pt_stat) {
        this.pt_stat = pt_stat;
    }

    public int getFirst_pay_time() {
        return first_pay_time;
    }

    public void setFirst_pay_time(int first_pay_time) {
        this.first_pay_time = first_pay_time;
    }

    public int getSecond_pay_time() {
        return second_pay_time;
    }

    public void setSecond_pay_time(int second_pay_time) {
        this.second_pay_time = second_pay_time;
    }

    public int getThree_pay_time() {
        return three_pay_time;
    }

    public void setThree_pay_time(int three_pay_time) {
        this.three_pay_time = three_pay_time;
    }

    public int getWorker_comments() {
        return worker_comments;
    }

    public void setWorker_comments(int worker_comments) {
        this.worker_comments = worker_comments;
    }

    public int getBoss_comments() {
        return boss_comments;
    }

    public void setBoss_comments(int boss_comments) {
        this.boss_comments = boss_comments;
    }

    public String getPrepaid_price() {
        return prepaid_price;
    }

    public void setPrepaid_price(String prepaid_price) {
        this.prepaid_price = prepaid_price;
    }

    public int getPrepaid_stat() {
        return prepaid_stat;
    }

    public void setPrepaid_stat(int prepaid_stat) {
        this.prepaid_stat = prepaid_stat;
    }

    public int getPrepaid_time() {
        return prepaid_time;
    }

    public void setPrepaid_time(int prepaid_time) {
        this.prepaid_time = prepaid_time;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLat_long() {
        return lat_long;
    }

    public void setLat_long(String lat_long) {
        this.lat_long = lat_long;
    }

    public String getAll_price() {
        return all_price;
    }

    public void setAll_price(String all_price) {
        this.all_price = all_price;
    }

    public int getPt_user_id() {
        return pt_user_id;
    }

    public void setPt_user_id(int pt_user_id) {
        this.pt_user_id = pt_user_id;
    }

    public int getHidden_phone() {
        return hidden_phone;
    }

    public void setHidden_phone(int hidden_phone) {
        this.hidden_phone = hidden_phone;
    }

    public int getWorker_frist_time() {
        return worker_frist_time;
    }

    public void setWorker_frist_time(int worker_frist_time) {
        this.worker_frist_time = worker_frist_time;
    }

    public int getWorker_second_time() {
        return worker_second_time;
    }

    public void setWorker_second_time(int worker_second_time) {
        this.worker_second_time = worker_second_time;
    }

    public int getWorker_three_time() {
        return worker_three_time;
    }

    public void setWorker_three_time(int worker_three_time) {
        this.worker_three_time = worker_three_time;
    }

    public int getBoss_prepaid_time() {
        return boss_prepaid_time;
    }

    public void setBoss_prepaid_time(int boss_prepaid_time) {
        this.boss_prepaid_time = boss_prepaid_time;
    }

    public BossUserBean getBoss_user() {
        return boss_user;
    }

    public void setBoss_user(BossUserBean boss_user) {
        this.boss_user = boss_user;
    }

    public int getAdd_blacklist() {
        return add_blacklist;
    }

    public void setAdd_blacklist(int add_blacklist) {
        this.add_blacklist = add_blacklist;
    }

    public ShowPtUserBean getShow_pt_user() {
        return show_pt_user;
    }

    public void setShow_pt_user(ShowPtUserBean show_pt_user) {
        this.show_pt_user = show_pt_user;
    }

    public List<String> getPt_imgs() {
        return pt_imgs;
    }

    public void setPt_imgs(List<String> pt_imgs) {
        this.pt_imgs = pt_imgs;
    }

    public static class BossUserBean {
        /**
         * user_phone : 18888888888
         */

        private String user_phone;
        private String nickname;
        private String user_img;

        public void setNickname(String mNickname) {
            nickname = mNickname;
        }

        public String getNickname() {
            return nickname;
        }

        public void setUser_img(String mUser_img) {
            user_img = mUser_img;
        }

        public String getUser_img() {
            return user_img;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }
    }

    public static class ShowPtUserBean {
        /**
         * worker_user : 666
         * first_pay_time : 1970.01.01
         * second_pay_time : 1970.01.01
         * three_pay_time : 1970.01.01
         */

        private String worker_user;
        private String first_pay_time;
        private String second_pay_time;
        private String three_pay_time;
        private String pt_user_id_time;

        public void setPt_user_id_time(String mPt_user_id_time) {
            pt_user_id_time = mPt_user_id_time;
        }

        public String getPt_user_id_time() {
            return pt_user_id_time;
        }

        public String getWorker_user() {
            return worker_user;
        }

        public void setWorker_user(String worker_user) {
            this.worker_user = worker_user;
        }

        public String getFirst_pay_time() {
            return first_pay_time;
        }

        public void setFirst_pay_time(String first_pay_time) {
            this.first_pay_time = first_pay_time;
        }

        public String getSecond_pay_time() {
            return second_pay_time;
        }

        public void setSecond_pay_time(String second_pay_time) {
            this.second_pay_time = second_pay_time;
        }

        public String getThree_pay_time() {
            return three_pay_time;
        }

        public void setThree_pay_time(String three_pay_time) {
            this.three_pay_time = three_pay_time;
        }
    }
}
