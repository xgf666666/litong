package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/7/9
 * describe:
 */
public class MemberBean {


    /**
     * system_id : 12
     * system_title : 月会员金额
     * system_content : 10
     */

    private int system_id;
    private String system_title;
    private String system_content;

    public int getSystem_id() {
        return system_id;
    }

    public void setSystem_id(int system_id) {
        this.system_id = system_id;
    }

    public String getSystem_title() {
        return system_title;
    }

    public void setSystem_title(String system_title) {
        this.system_title = system_title;
    }

    public String getSystem_content() {
        return system_content;
    }

    public void setSystem_content(String system_content) {
        this.system_content = system_content;
    }

    @Override
    public String toString() {
        return "MemberBean{" +
                "system_id=" + system_id +
                ", system_title='" + system_title + '\'' +
                ", system_content='" + system_content + '\'' +
                '}';
    }
}
