package com.jie.seriviceimpl;

import com.jie.bean.Device;
import com.jie.dao.DeviceMapper;
import com.jie.dao.UserMapper;
import com.jie.service.deviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class deviceImpl implements deviceService {
    @Autowired
    private DeviceMapper mapper=null;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
    public Device getDevice(Integer deviceId) {
        return mapper.getDevice(deviceId);
    }

}
