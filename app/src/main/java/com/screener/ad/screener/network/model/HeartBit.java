package com.screener.ad.screener.network.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

public class HeartBit {
    public static class Up {
        private final String action;
        private String deviceid;
        @JSONField(name = "osver")
        private String osVersion;
        @JSONField(name = "appver")
        private String appVerion;
        @JSONField(name = "starttime")
        private long startTime;
        @JSONField(name = "totalspace")
        private double totalSpace;
        @JSONField(name = "freespace")
        private double freeSpace;
        @JSONField(name = "longitude")
        private double longitude;
        @JSONField(name = "latitude")
        private double latitude;
        @JSONField(name = "memoryspace")
        private double memorySpace;
        @JSONField(name = "curmd5")
        private String currentMd5;
        @JSONField(name = "filelist")
        private String fileList;

        public Up() {
            this.action = "uploadinfo";
        }

        public String getAction() {
            return action;
        }

        public String getDeviceid() {
            return deviceid;
        }

        public void setDeviceid(String deviceid) {
            this.deviceid = deviceid;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }

        public String getAppVerion() {
            return appVerion;
        }

        public void setAppVerion(String appVerion) {
            this.appVerion = appVerion;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public double getTotalSpace() {
            return totalSpace;
        }

        public void setTotalSpace(double totalSpace) {
            this.totalSpace = totalSpace;
        }

        public double getFreeSpace() {
            return freeSpace;
        }

        public void setFreeSpace(double freeSpace) {
            this.freeSpace = freeSpace;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getMemorySpace() {
            return memorySpace;
        }

        public void setMemorySpace(double memorySpace) {
            this.memorySpace = memorySpace;
        }

        public String getCurrentMd5() {
            return currentMd5;
        }

        public void setCurrentMd5(String currentMd5) {
            this.currentMd5 = currentMd5;
        }

        public String getFileList() {
            return fileList;
        }

        public void setFileList(String fileList) {
            this.fileList = fileList;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    public static class Down {
        private int code;
        private String message;

        @JSONField(name = "getadlist")
        private List<AdEntry> adEntries;

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

        public List<AdEntry> getAdEntries() {
            return adEntries;
        }

        public void setAdEntries(List<AdEntry> adEntries) {
            this.adEntries = adEntries;
        }
    }
}