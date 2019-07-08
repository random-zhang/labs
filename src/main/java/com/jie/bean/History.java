package com.jie.bean;

public class History {
    private String historyid;

    private Long subid;

    public String getHistoryid() {
        return historyid;
    }

    public void setHistoryid(String historyid) {
        this.historyid = historyid == null ? null : historyid.trim();
    }

    public Long getSubid() {
        return subid;
    }

    public void setSubid(Long subid) {
        this.subid = subid;
    }
}