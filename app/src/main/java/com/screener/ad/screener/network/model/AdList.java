package com.screener.ad.screener.network.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class AdList {
    public static class Up {
        private final String action;
        private String deviceId;

        public Up(String deviceId) {
            this.action = "getadlist";
            this.deviceId = deviceId;
        }

        public String getAction() {
            return action;
        }

        public String getDeviceId() {
            return deviceId;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    public static class Down {
        private int code;
        private String message;
        @JSONField(name = "adlist")
        private List<AdEntry> adEntryList;

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

        public List<AdEntry> getAdEntryList() {
            return adEntryList;
        }

        public void setAdEntryList(List<AdEntry> adEntryList) {
            this.adEntryList = adEntryList;
        }
    }
}