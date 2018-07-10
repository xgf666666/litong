package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
public class WorkEvaluateBean {

    /**
     * total : 2
     * per_page : 2
     * current_page : 1
     * last_page : 1
     * data : [{"com_id":1,"pt_id":1,"com_content":"99999999999988888888888888888888","com_imgs":["/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"],"add_time":"1970.01.01","user_id":1,"score":25,"user_data":{"nickname":"66676867","user_phone":"13682873453","user_img":"/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"}},{"com_id":3,"pt_id":1,"com_content":"1","com_imgs":["/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"],"add_time":"2018.06.28","user_id":1,"score":0,"user_data":{"nickname":"66676867","user_phone":"13682873453","user_img":"/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"}}]
     */

    private int total;
    private int per_page;
    private int current_page;
    private int last_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * com_id : 1
         * pt_id : 1
         * com_content : 99999999999988888888888888888888
         * com_imgs : ["/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"]
         * add_time : 1970.01.01
         * user_id : 1
         * score : 25
         * user_data : {"nickname":"66676867","user_phone":"13682873453","user_img":"/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"}
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
             * nickname : 66676867
             * user_phone : 13682873453
             * user_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
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
}
