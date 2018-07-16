package com.weibiaogan.litong.entity;

/**
 * author: xiaoguagnfei
 * date: 2018/7/16
 * describe:
 */
public class PayBean {

    /**
     * data : {"return_code":"SUCCESS","return_msg":"OK","appid":"wx4fc9ff6e5c76f8bc","mch_id":"1509644341","nonce_str":"c23px7f03k673e151clzcwaxs14qxxa1","sign":"7282F5B6E3A6D13254870BA7E5C45F9F","result_code":"SUCCESS","prepay_id":"wx12155521451379ec2f556b191920241559","trade_type":"APP","time":1531382121}
     * type : wechat
     */

    private DataBean data;
    private String type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean {
        /**
         * return_code : SUCCESS
         * return_msg : OK
         * appid : wx4fc9ff6e5c76f8bc
         * mch_id : 1509644341
         * nonce_str : c23px7f03k673e151clzcwaxs14qxxa1
         * sign : 7282F5B6E3A6D13254870BA7E5C45F9F
         * result_code : SUCCESS
         * prepay_id : wx12155521451379ec2f556b191920241559
         * trade_type : APP
         * time : 1531382121
         */

        private String return_code;
        private String return_msg;
        private String appid;
        private String mch_id;
        private String nonce_str;
        private String sign;
        private String result_code;
        private String prepay_id;
        private String trade_type;
        private int time;

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
