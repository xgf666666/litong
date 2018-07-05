package com.weibiaogan.litong.entity;

import com.google.gson.annotations.SerializedName;

/**
 * author: Gubr
 * date: 2018/5/24
 * describe:
 */

public class ImageBean {

    /**
     * imgUrl : /uploads/image/20180524/20180524117008.png
     */

    @SerializedName("imgUrl")
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
