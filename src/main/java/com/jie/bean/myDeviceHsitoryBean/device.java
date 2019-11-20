package com.jie.bean.myDeviceHsitoryBean;

import java.util.ArrayList;

public class device {
    private int deviceId;
    private ArrayList<sub> subs;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public ArrayList<sub> getSubs() {
        return subs;
    }

    public void setSubs(ArrayList<sub> subs) {
        this.subs = subs;
    }
}
