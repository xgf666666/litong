package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: HuaiXianZhong
 * date: 2018/7/12
 * describe:
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
     * pt_stat : 2
     * first_pay_time : 0
     * second_pay_time : 0
     * three_pay_time : 0
     * worker_comments : 0
     * boss_comments : 0
     * prepaid_price : 0.00
     * prepaid_stat : 0
     * prepaid_time : 0
     * add_time : 2018.06.25
     * pt_imgs : ["/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"]
     * user_id : 1
     * lat_long : 113.222801,23.169136
     * all_price : 600.00
     * pt_user_id : 0
     * hidden_phone : 0
     * worker_frist_time : 0
     * worker_second_time : 0
     * worker_three_time : 0
     * boss_prepaid_time : 0
     * boss_user : {"user_id":1,"user_phone":"13682873453","user_pwd":"fa509a31fab15f6613c0b8742e51fba9","user_img":"/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","user_qrcode":"/uploads/user_qrcode/18926146071.png","user_sex":2,"user_qq":"","user_addtime":1525762728,"user_int":0,"gr_id":2,"token":"Z0quVkgQ5TGh4jtktx","balance":"1.00","returns_balance":"0.40","buy_balance":"0.00","balance_payment":"fa509a31fab15f6613c0b8742e51fba9","pid":"0","invite":"13682873453","nickname":"我就是我不一样的烟火","openid":"","province":"","city":"","area":"","address":"","is_del":0,"boss_stat":1,"worker_stat":1,"qqid":"","lat_long":"116.557684,23.192033","boss_lat_long":"113.321803,23.117347","members_end_time":1533225600,"worker_free":1}
     * add_blacklist : 0
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

    public List<String> getPt_imgs() {
        return pt_imgs;
    }

    public void setPt_imgs(List<String> pt_imgs) {
        this.pt_imgs = pt_imgs;
    }

    public static class BossUserBean {
        /**
         * user_id : 1
         * user_phone : 13682873453
         * user_pwd : fa509a31fab15f6613c0b8742e51fba9
         * user_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
         * user_qrcode : /uploads/user_qrcode/18926146071.png
         * user_sex : 2
         * user_qq :
         * user_addtime : 1525762728
         * user_int : 0
         * gr_id : 2
         * token : Z0quVkgQ5TGh4jtktx
         * balance : 1.00
         * returns_balance : 0.40
         * buy_balance : 0.00
         * balance_payment : fa509a31fab15f6613c0b8742e51fba9
         * pid : 0
         * invite : 13682873453
         * nickname : 我就是我不一样的烟火
         * openid :
         * province :
         * city :
         * area :
         * address :
         * is_del : 0
         * boss_stat : 1
         * worker_stat : 1
         * qqid :
         * lat_long : 116.557684,23.192033
         * boss_lat_long : 113.321803,23.117347
         * members_end_time : 1533225600
         * worker_free : 1
         */

        private int user_id;
        private String user_phone;
        private String user_pwd;
        private String user_img;
        private String user_qrcode;
        private int user_sex;
        private String user_qq;
        private int user_addtime;
        private int user_int;
        private int gr_id;
        private String token;
        private String balance;
        private String returns_balance;
        private String buy_balance;
        private String balance_payment;
        private String pid;
        private String invite;
        private String nickname;
        private String openid;
        private String province;
        private String city;
        private String area;
        private String address;
        private int is_del;
        private int boss_stat;
        private int worker_stat;
        private String qqid;
        private String lat_long;
        private String boss_lat_long;
        private int members_end_time;
        private int worker_free;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        public String getUser_qrcode() {
            return user_qrcode;
        }

        public void setUser_qrcode(String user_qrcode) {
            this.user_qrcode = user_qrcode;
        }

        public int getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(int user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_qq() {
            return user_qq;
        }

        public void setUser_qq(String user_qq) {
            this.user_qq = user_qq;
        }

        public int getUser_addtime() {
            return user_addtime;
        }

        public void setUser_addtime(int user_addtime) {
            this.user_addtime = user_addtime;
        }

        public int getUser_int() {
            return user_int;
        }

        public void setUser_int(int user_int) {
            this.user_int = user_int;
        }

        public int getGr_id() {
            return gr_id;
        }

        public void setGr_id(int gr_id) {
            this.gr_id = gr_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getReturns_balance() {
            return returns_balance;
        }

        public void setReturns_balance(String returns_balance) {
            this.returns_balance = returns_balance;
        }

        public String getBuy_balance() {
            return buy_balance;
        }

        public void setBuy_balance(String buy_balance) {
            this.buy_balance = buy_balance;
        }

        public String getBalance_payment() {
            return balance_payment;
        }

        public void setBalance_payment(String balance_payment) {
            this.balance_payment = balance_payment;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getInvite() {
            return invite;
        }

        public void setInvite(String invite) {
            this.invite = invite;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public int getBoss_stat() {
            return boss_stat;
        }

        public void setBoss_stat(int boss_stat) {
            this.boss_stat = boss_stat;
        }

        public int getWorker_stat() {
            return worker_stat;
        }

        public void setWorker_stat(int worker_stat) {
            this.worker_stat = worker_stat;
        }

        public String getQqid() {
            return qqid;
        }

        public void setQqid(String qqid) {
            this.qqid = qqid;
        }

        public String getLat_long() {
            return lat_long;
        }

        public void setLat_long(String lat_long) {
            this.lat_long = lat_long;
        }

        public String getBoss_lat_long() {
            return boss_lat_long;
        }

        public void setBoss_lat_long(String boss_lat_long) {
            this.boss_lat_long = boss_lat_long;
        }

        public int getMembers_end_time() {
            return members_end_time;
        }

        public void setMembers_end_time(int members_end_time) {
            this.members_end_time = members_end_time;
        }

        public int getWorker_free() {
            return worker_free;
        }

        public void setWorker_free(int worker_free) {
            this.worker_free = worker_free;
        }
    }
}
