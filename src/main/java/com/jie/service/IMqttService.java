package com.jie.service;

import com.alibaba.fastjson.JSONObject;

public interface IMqttService {
    public boolean runDevice(int subId,int userId);
    public void interruptDevice(int subId);
    boolean openDevice(int subId);
    boolean closeDevice(int subId);
    boolean sendResultToMobile(int userId,String json);
}
