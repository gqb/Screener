package com.screener.ad.screener.network.model;

import com.alibaba.fastjson.JSONObject;

public class StartApp {
    public static class Up{
        private final String action;
        private String deviceid;

        public Up(String deviceid) {
            this.action = "startapp";
            this.deviceid = deviceid;
        }

        public String getAction() {
            return action;
        }

        public String getDeviceid() {
            return deviceid;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }
    public static class Down {
        private int code;
        private String message;
        private String picture;

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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}