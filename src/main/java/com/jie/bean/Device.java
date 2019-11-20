package com.jie.bean;

public class Device {
    private Integer deviceid;
    private String devicename;
    private String devicepiture;
    private Integer subId;
    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public String getDevicepiture() {
        return devicepiture;
    }

    public void setDevicepiture(String devicepiture) {
        this.devicepiture = devicepiture == null ? null : devicepiture.trim();
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }
}