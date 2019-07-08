package com.jie.bean;

public class DHistory {
    private String historyid;

    private String deviceid;

    private String note;

    public String getHistoryid() {
        return historyid;
    }

    public void setHistoryid(String historyid) {
        this.historyid = historyid == null ? null : historyid.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}