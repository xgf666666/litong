package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/7/16
 * describe:
 */
public class PublicProjectsBean {

    /**
     * pt_id : 506
     */

    private String pt_id;
    private String prepaid_price;

    public void setPrepaid_price(String mPrepaid_price) {
        prepaid_price = mPrepaid_price;
    }

    public String getPrepaid_price() {
        return prepaid_price;
    }

    public String getPt_id() {
        return pt_id;
    }

    public void setPt_id(String pt_id) {
        this.pt_id = pt_id;
    }
}
