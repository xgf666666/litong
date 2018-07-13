package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/7/13
 * describe:
 */
public class ShareUserBean {

    /**
     * user : {"user_phone":"13682873453","nickname":"我就是我不一样的烟火","user_img":"/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"}
     * url : http://litong.goodbooy.cn/Home/Login/reg/pid/13682873453.html
     * share : {"share_title":"名创酒品分享标题","share_content":"分享内容描述","share_img":"http://jiuping.goodbooy.cn/static/api/images/share_img.png"}
     */

    private UserBean user;
    private String url;
    private ShareBean share;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public static class UserBean {
        /**
         * user_phone : 13682873453
         * nickname : 我就是我不一样的烟火
         * user_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
         */

        private String user_phone;
        private String nickname;
        private String user_img;

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
    }

    public static class ShareBean {
        /**
         * share_title : 名创酒品分享标题
         * share_content : 分享内容描述
         * share_img : http://jiuping.goodbooy.cn/static/api/images/share_img.png
         */

        private String share_title;
        private String share_content;
        private String share_img;

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_content() {
            return share_content;
        }

        public void setShare_content(String share_content) {
            this.share_content = share_content;
        }

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }
    }
}
