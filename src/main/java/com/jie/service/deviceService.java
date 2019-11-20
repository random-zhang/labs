package com.jie.service;
import com.jie.bean.Device;

import java.util.HashMap;
import java.util.Map;

public interface deviceService {//设备服务层
    Device getDevice(Integer deviceId);
    Map getLinkGuideInfo(int deviceId);
    boolean updateDevice(Map map);
    String getDeviceName(int deviceId);
    String getSubNameById(int deviceId,int subId);
    HashMap getManageUsersActivityInfo(Map map);
    Integer getDeviceIdBySubId(int subId);
}
