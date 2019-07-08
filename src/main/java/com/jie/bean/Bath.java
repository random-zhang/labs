package com.jie.bean;

public class Bath {
    private Integer subId;

    private Integer deviceId;

    private Double sv;

    private Double cv;

    private Double st;

    private Double ct;

    private String wssid;

    private Coordinates coordinates;


    public Double getSv() {
        return sv;
    }

    public void setSv(Double sv) {
        this.sv = sv;
    }

    public Double getCv() {
        return cv;
    }

    public void setCv(Double cv) {
        this.cv = cv;
    }

    public Double getSt() {
        return st;
    }

    public void setSt(Double st) {
        this.st = st;
    }

    public Double getCt() {
        return ct;
    }

    public void setCt(Double ct) {
        this.ct = ct;
    }

    public String getWssid() {
        return wssid;
    }

    public void setWssid(String wssid) {
        this.wssid = wssid == null ? null : wssid.trim();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
}