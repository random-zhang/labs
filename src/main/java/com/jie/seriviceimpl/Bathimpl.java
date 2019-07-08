package com.jie.seriviceimpl;

import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import com.jie.bean.Device;
import com.jie.dao.BathMapper;
import com.jie.dao.DeviceMapper;
import com.jie.service.bathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Bathimpl implements bathService {//水浴锅服务实现
    @Autowired
    private BathMapper mapper=null;
    @Override
    public Device getDevice(Integer deviceId) {
        return null;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
    public Bath getBath(Integer subId) {
        return mapper.getBath(subId);
    }
    @Override
    public int settingBath(Bath bath) {//设定水浴锅的2值
        return  mapper.settingBath(bath);
    }

    @Override
    public int updateCv(int subId, double cv) {
        return mapper.updateCv(subId,cv);
    }

    @Override
    public double getCv(int subId) {
        return mapper.getCv(subId);
    }

    @Override
    public int insertCoordinate(int subId, Coordinates.Point coordinate) {
        System.out.println(coordinate.toString());
        return mapper.insertCoordinate(subId,coordinate.toString());
    }

    @Override
    public int clearCoordinate(int subId) {
        return mapper.clearCoordinate(subId);
    }

    @Override
    public Coordinates getCoordinates(int subId) {//在这里进行转换
        //String coordinates=
        return  mapper.getCoordinates(subId);
    }
    @Override
    public Coordinates.Point getCoordinate(int subId) {
        return mapper.getCoordinate(subId);
    }

    @Override
    public int setBathStatus(int subId,int status) {
        return mapper.setBathStatus(subId,status);
    }

    @Override
    public int getBathStatus(int subId) {
        return mapper.getBathStatus(subId);
    }
}
