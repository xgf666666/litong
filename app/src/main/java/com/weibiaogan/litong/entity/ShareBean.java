package com.weibiaogan.litong.entity;

import com.google.gson.annotations.SerializedName;

/**
 * author: Gubr
 * date: 2018/6/1
 * describe:
 */

public class ShareBean {

    /**
     * user_id : 1
     * user_phone : 13682873453
     * user_pwd : fa509a31fab15f6613c0b8742e51fba9
     * user_img : /uploads/20180517/fcea65fad4a4c81ac9f41520c1add22f.png
     * user_qrcode : /uploads/user_qrcode/18926146071.png
     * user_sex : 2
     * user_qq : null
     * user_addtime : 1525762728
     * user_int : 0
     * gr_id : 1
     * token : QsdN54NVKJlCTeH2G6
     * balance : 0.40
     * returns_balance : 0.40
     * buy_balance : 0.00
     * balance_payment : 1
     * pid : 0
     * invite : 13682873453
     * nickname : 我就是我不一样的烟火
     * openid :
     * province : null
     * city : null
     * area : null
     * address : null
     * is_del : 0
     * url : http://192.168.0.150/Home/Login/reg/pid/13682873453.html
     * share_title : 名创酒品分享标题
     * share_content : 分享内容描述
     * share_img : http://jiuping.goodbooy.cn/static/api/images/share_img.png
     */

    @SerializedName("user_id")
    private int userId;
    @SerializedName("user_phone")
    private String userPhone;
    @SerializedName("user_pwd")
    private String userPwd;
    @SerializedName("user_img")
    private String userImg;
    @SerializedName("user_qrcode")
    private String userQrcode;
    @SerializedName("user_sex")
    private int userSex;
    @SerializedName("user_qq")
    private Object userQq;
    @SerializedName("user_addtime")
    private int userAddtime;
    @SerializedName("user_int")
    private int userInt;
    @SerializedName("gr_id")
    private int grId;
    @SerializedName("token")
    private String token;
    @SerializedName("balance")
    private String balance;
    @SerializedName("returns_balance")
    private String returnsBalance;
    @SerializedName("buy_balance")
    private String buyBalance;
    @SerializedName("balance_payment")
    private String balancePayment;
    @SerializedName("pid")
    private String pid;
    @SerializedName("invite")
    private String invite;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("openid")
    private String openid;
    @SerializedName("province")
    private Object province;
    @SerializedName("city")
    private Object city;
    @SerializedName("area")
    private Object area;
    @SerializedName("address")
    private Object address;
    @SerializedName("is_del")
    private int isDel;
    @SerializedName("url")
    private String url;
    @SerializedName("share_title")
    private String shareTitle;
    @SerializedName("share_content")
    private String shareContent;
    @SerializedName("share_img")
    private String shareImg;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserQrcode() {
        return userQrcode;
    }

    public void setUserQrcode(String userQrcode) {
        this.userQrcode = userQrcode;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public Object getUserQq() {
        return userQq;
    }

    public void setUserQq(Object userQq) {
        this.userQq = userQq;
    }

    public int getUserAddtime() {
        return userAddtime;
    }

    public void setUserAddtime(int userAddtime) {
        this.userAddtime = userAddtime;
    }

    public int getUserInt() {
        return userInt;
    }

    public void setUserInt(int userInt) {
        this.userInt = userInt;
    }

    public int getGrId() {
        return grId;
    }

    public void setGrId(int grId) {
        this.grId = grId;
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

    public String getReturnsBalance() {
        return returnsBalance;
    }

    public void setReturnsBalance(String returnsBalance) {
        this.returnsBalance = returnsBalance;
    }

    public String getBuyBalance() {
        return buyBalance;
    }

    public void setBuyBalance(String buyBalance) {
        this.buyBalance = buyBalance;
    }

    public String getBalancePayment() {
        return balancePayment;
    }

    public void setBalancePayment(String balancePayment) {
        this.balancePayment = balancePayment;
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

    public Object getProvince() {
        return province;
    }

    public void setProvince(Object province) {
        this.province = province;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }
}
