package com.jie.bean.myDeviceHsitoryBean;

import java.util.ArrayList;

public class sub {
    private int subId;
    private ArrayList<history> historyIds;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public ArrayList<history> getHistoryIds() {
        return historyIds;
    }

    public void setHistoryIds(ArrayList<history> historyIds) {
        this.historyIds = historyIds;
    }
}
