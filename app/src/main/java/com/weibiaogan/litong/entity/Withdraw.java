package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/8/10
 * describe:
 */
public class Withdraw {

    /**
     * system_id : 14
     * system_title : 提现扣除手续费
     * system_content : 提现将扣除1%为手续费，即提现100元，到账99元
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
}
