package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
public class WorkEvaluateBean {

    /**
     * com_id : 2
     * pt_id : 1
     * com_content : 我喜欢这个产品
     * com_imgs : ["/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"]
     * add_time : 2018.06.28
     * user_id : 2
     * score : 100
     * user_data : {"nickname":"我就是我不一样的烟火","user_phone":"15812691920","user_img":"/uploads/image/20180528/20180528658788.png"}
     */

    private int com_id;
    private int pt_id;
    private String com_content;
    private String add_time;
    private int user_id;
    private int score;
    private UserDataBean user_data;
    private List<String> com_imgs;

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getPt_id() {
        return pt_id;
    }

    public void setPt_id(int pt_id) {
        this.pt_id = pt_id;
    }

    public String getCom_content() {
        return com_content;
    }

    public void setCom_content(String com_content) {
        this.com_content = com_content;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserDataBean getUser_data() {
        return user_data;
    }

    public void setUser_data(UserDataBean user_data) {
        this.user_data = user_data;
    }

    public List<String> getCom_imgs() {
        return com_imgs;
    }

    public void setCom_imgs(List<String> com_imgs) {
        this.com_imgs = com_imgs;
    }

    public static class UserDataBean {
        /**
         * nickname : 我就是我不一样的烟火
         * user_phone : 15812691920
         * user_img : /uploads/image/20180528/20180528658788.png
         */

        private String nickname;
        private String user_phone;
        private String user_img;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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
    }
}
