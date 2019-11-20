package com.jie.bean;

import java.util.Date;

public class Bath {
    private Integer subId,deviceId,status;

    private Double sv,st;

    private String wssid,points;

    private Coordinates coordinates;

    private Date startTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Double getSv() {
        return sv;
    }

    public void setSv(Double sv) {
        this.sv = sv;
    }

    public Double getSt() {
        return st;
    }

    public void setSt(Double st) {
        this.st = st;
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