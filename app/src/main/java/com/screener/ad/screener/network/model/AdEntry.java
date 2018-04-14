package com.screener.ad.screener.network.model;

public class AdEntry {
    private String adfilepath;
    private String md5;
    private String timelength;
    private String startdate;
    private String enddate;
    private String chargetype;

    public String getAdfilepath() {
        return adfilepath;
    }

    public void setAdfilepath(String adfilepath) {
        this.adfilepath = adfilepath;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getTimelength() {
        return timelength;
    }

    public void setTimelength(String timelength) {
        this.timelength = timelength;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getChargetype() {
        return chargetype;
    }

    public void setChargetype(String chargetype) {
        this.chargetype = chargetype;
    }
}