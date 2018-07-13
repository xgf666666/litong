package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: HuaiXianZhong
 * date: 2018/7/6
 * describe:
 */
public class HomeBean {


    private List<ProjectBean> project;
    private List<AdveBean> adve;
    private List<WorkerBean> worker;
    private List<StoreBean> store;

    public List<ProjectBean> getProject() {
        return project;
    }

    public void setProject(List<ProjectBean> project) {
        this.project = project;
    }

    public List<AdveBean> getAdve() {
        return adve;
    }

    public void setAdve(List<AdveBean> adve) {
        this.adve = adve;
    }

    public List<WorkerBean> getWorker() {
        return worker;
    }

    public void setWorker(List<WorkerBean> worker) {
        this.worker = worker;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public static class ProjectBean {
        /**
         * pt_id : 2
         * pt_name : 广州二运集团有限公司: 西洲中公交立体充电桩停车场项目
         * end_time : 2018.08.08
         * all_price : 600.00
         * distance : 12376852
         */

        private int pt_id;
        private String pt_name;
        private String end_time;
        private String all_price;
        private int distance;
        private String pt_imgs;

        public void setPt_imgs(String mPt_imgs) {
            pt_imgs = mPt_imgs;
        }

        public String getPt_imgs() {
            return pt_imgs;
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

    public static class AdveBean {
        /**
         * ad_id : 1
         * ad_img : /uploads/20180704/15149dbf7fb5fabd5a6d264331361972.png
         * pro_id : 2
         * ad_sort : 1888888888
         * ad_url : 1
         * ad_title : 测试188888888888888
         * ad_type : 0
         * ad_st : 1
         */

        private int ad_id;
        private String ad_img;
        private int pro_id;
        private int ad_sort;
        private String ad_url;
        private String ad_title;
        private int ad_type;
        private int ad_st;

        public int getAd_id() {
            return ad_id;
        }

        public void setAd_id(int ad_id) {
            this.ad_id = ad_id;
        }

        public String getAd_img() {
            return ad_img;
        }

        public void setAd_img(String ad_img) {
            this.ad_img = ad_img;
        }

        public int getPro_id() {
            return pro_id;
        }

        public void setPro_id(int pro_id) {
            this.pro_id = pro_id;
        }

        public int getAd_sort() {
            return ad_sort;
        }

        public void setAd_sort(int ad_sort) {
            this.ad_sort = ad_sort;
        }

        public String getAd_url() {
            return ad_url;
        }

        public void setAd_url(String ad_url) {
            this.ad_url = ad_url;
        }

        public String getAd_title() {
            return ad_title;
        }

        public void setAd_title(String ad_title) {
            this.ad_title = ad_title;
        }

        public int getAd_type() {
            return ad_type;
        }

        public void setAd_type(int ad_type) {
            this.ad_type = ad_type;
        }

        public int getAd_st() {
            return ad_st;
        }

        public void setAd_st(int ad_st) {
            this.ad_st = ad_st;
        }
    }

    public static class WorkerBean {
        /**
         * user_id : 2
         * gr_id : 1
         * user_phone : 15812691920
         * user_img : /uploads/image/20180528/20180528658788.png
         * distance : 12376852
         */

        private int user_id;
        private int gr_id;
        private String user_phone;
        private String user_img;
        private int distance;
        private String nickname;
        private String worker_service;

        public void setWorker_service(String mWorker_service) {
            worker_service = mWorker_service;
        }

        public String getWorker_service() {
            return worker_service;
        }

        public void setNickname(String mNickname) {
            nickname = mNickname;
        }

        public String getNickname() {
            return nickname;
        }

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

    public static class StoreBean {
        /**
         * st_id : 1
         * st_name : 你是风儿我是沙
         * st_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
         * st_type : 沙子
         * distance : 12376852
         */

        private int st_id;
        private String st_name;
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
}
