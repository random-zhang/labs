package com.jie.seriviceimpl;

import com.jie.bean.Device;
import com.jie.dao.BathMapper;
import com.jie.dao.DeviceMapper;
import com.jie.dao.UserMapper;
import com.jie.service.deviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class deviceImpl implements deviceService {
    private final static int BATH=1;
    @Autowired
    private DeviceMapper deviceMapper = null;
    @Autowired
    private BathMapper bathMapper=null;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Device getDevice(Integer deviceId) {
        return deviceMapper.getDevice(deviceId);
    }

    @Override
    public Map getLinkGuideInfo(int deviceId) {
        return deviceMapper.getLinkGuideInfo(deviceId);
    }

    @Override
    public boolean updateDevice(Map map) {//根据
        int deviceId = (int) map.get("deviceId");
        int row = 0;
        switch (deviceId) {
            case 1: {//水浴guo
                row = bathMapper.updateBath(map);
            }
        }
        if (row != 1)
            return false;
        return true;
    }

    @Override
    public String getDeviceName(int deviceId) {
        return  deviceMapper.getDeviceName(deviceId);
    }

    @Override
    public String getSubNameById(int deviceId, int subId) {

        return null;
    }

    @Override
    public HashMap getManageUsersActivityInfo(Map map) {
        //首先获取（userId,userName)
        ArrayList<HashMap> users=new ArrayList<>();
        int subId=(int)map.get("subId");
          users=deviceMapper.getNonManagerUserIdAndUserNameBySubId(subId);
          HashMap hashMap=new HashMap();
          //int userId=(int)map.get("userId");
          int deviceId=(int)map.get("deviceId");
          switch (deviceId){//找到当前设备状态、使用者
              case BATH :{
                  hashMap=bathMapper.findCurrentStatusAndUser(subId);
              }
          }
          HashMap result=new HashMap();
          result.put("users",users);
          result.putAll(hashMap);
        //然后获取status
        return result;
    }

    @Override
    public Integer getDeviceIdBySubId(int subId) {
        return deviceMapper.getDeviceIdBySubId(subId);
    }

}
