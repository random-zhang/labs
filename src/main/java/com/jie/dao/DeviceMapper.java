package com.jie.dao;

import com.jie.bean.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface DeviceMapper {
    int insert(Device record);
    int insertSelective(Device record);
    Device getDevice(int deviceId);
    Map getLinkGuideInfo(int deviceId );
    String getDeviceName(int deviceId);
    ArrayList<HashMap> getNonManagerUserIdAndUserNameBySubId(int subId);
    int getDeviceIdBySubId(int subId);
}