package com.weibiaogan.litong.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author: Gubr
 * date: 2018/5/21
 * describe:用户中心  bean
 */

public class UserCenterBean implements Serializable {

    /**
     * user : {"user_phone":"15812691920","nickname":"15812691920","user_img":"","balance":0,"returns_balance":0,"buy_balance":0,"lack":0}
     * order : {"wait_payment":0,"wait_delivery":0,"wait_goods":0}
     */

    @SerializedName("user")
    private UserBean user;
    @SerializedName("order")
    private OrderBean order;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public static class UserBean implements Serializable {
        /**
         * user_phone : 15812691920
         * nickname : 15812691920
         * user_img :
         * balance : 0
         * returns_balance : 0
         * buy_balance : 0
         * lack : 0
         */

        @SerializedName("user_phone")
        private String userPhone;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("user_img")
        private String userImg;
        @SerializedName("balance")
        private double balance;
        @SerializedName("returns_balance")
        private int returnsBalance;


        @SerializedName("buy_balance")

        private int buyBalance;
        @SerializedName("user_sex")
        private int userSex;
        @SerializedName("lack")
        private int lack;
        @SerializedName("gr_id")
        private int grid;
        /**
         * balance : 0.00
         * boss_stat : 0
         * worker_stat : 0
         */

        @SerializedName("boss_stat")
        private int bossStat;
        @SerializedName("worker_stat")
        private int workerStat;


        public int getGrid() {
            return grid;
        }

        public void setGrid(int grid) {
            this.grid = grid;
        }

        public int getUserSex() {
            return userSex;
        }

        public void setUserSex(int userSex) {
            this.userSex = userSex;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public int getReturnsBalance() {
            return returnsBalance;
        }

        public void setReturnsBalance(int returnsBalance) {
            this.returnsBalance = returnsBalance;
        }

        public int getBuyBalance() {
            return buyBalance;
        }

        public void setBuyBalance(int buyBalance) {
            this.buyBalance = buyBalance;
        }

        public int getLack() {
            return lack;
        }

        public void setLack(int lack) {
            this.lack = lack;
        }

        public int getBossStat() {
            return bossStat;
        }

        public void setBossStat(int bossStat) {
            this.bossStat = bossStat;
        }

        public int getWorkerStat() {
            return workerStat;
        }

        public void setWorkerStat(int workerStat) {
            this.workerStat = workerStat;
        }
    }

    public static class OrderBean implements Serializable {
        /**
         * wait_payment : 0
         * wait_delivery : 0
         * wait_goods : 0
         */

        @SerializedName("wait_payment")
        private int waitPayment;
        @SerializedName("wait_delivery")
        private int waitDelivery;
        @SerializedName("wait_goods")
        private int waitGoods;

        public int getWaitPayment() {
            return waitPayment;
        }

        public void setWaitPayment(int waitPayment) {
            this.waitPayment = waitPayment;
        }

        public int getWaitDelivery() {
            return waitDelivery;
        }

        public void setWaitDelivery(int waitDelivery) {
            this.waitDelivery = waitDelivery;
        }

        public int getWaitGoods() {
            return waitGoods;
        }

        public void setWaitGoods(int waitGoods) {
            this.waitGoods = waitGoods;
        }
    }
}
