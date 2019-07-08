package com.jie.dao;

import com.jie.bean.Device;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMapper {
    int insert(Device record);
    int insertSelective(Device record);
    Device getDevice(int deviceId);
}