package com.screener.ad.screener.network.model;

import com.alibaba.fastjson.JSONObject;

public class Error {

    public static class Up {
        private final String action;
        private String deviceid;
        private int erroType;
        private String md5;

        public Up(String deviceid, int erroType, String md5) {
            this.action = "uploaderror";
            this.deviceid = deviceid;
            this.erroType = erroType;
            this.md5 = md5;
        }

        public String getAction() {
            return action;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public int getErroType() {
            return erroType;
        }

        public String getMd5() {
            return md5;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    public static class Down {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}