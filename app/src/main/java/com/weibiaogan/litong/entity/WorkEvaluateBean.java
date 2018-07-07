package com.weibiaogan.litong.entity;

import java.util.List;

/**
 * author: HuaiXianZhong
 * date: 2018/7/7
 * describe:
 */
public class WorkEvaluateBean {


    /**
     * com_id : 1
     * pi_id : 1
     * com_content : 1
     * com_imgs : ["/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png","/uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png"]
     * add_time : 1970-01-01 08:00:01
     * user_id : 1
     */

    private int com_id;
    private int pi_id;
    private String com_content;
    private String add_time;
    private int user_id;
    private List<String> com_imgs;

    public int getCom_id() {
        return com_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public int getPi_id() {
        return pi_id;
    }

    public void setPi_id(int pi_id) {
        this.pi_id = pi_id;
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

    public List<String> getCom_imgs() {
        return com_imgs;
    }

    public void setCom_imgs(List<String> com_imgs) {
        this.com_imgs = com_imgs;
    }
}
