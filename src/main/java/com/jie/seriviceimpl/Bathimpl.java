package com.jie.seriviceimpl;

import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import com.jie.bean.Device;
import com.jie.bean.Point;
import com.jie.dao.BathMapper;
import com.jie.service.IMqttService;
import com.jie.service.bathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Map;

@Service
public  class Bathimpl implements bathService {//水浴锅服务实现
    @Autowired
    private BathMapper mapper = null;
    @Autowired
    private IMqttService mqttService = null;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Bath getBath(Integer subId) {
        return mapper.getBath(subId);
    }

    @Override
    public int settingBath(Bath bath) {//设定水浴锅的2值
        return mapper.settingBath(bath);
    }

    @Override
    public int updateCv(int subId, double cv) {
        return mapper.updateCv(subId, cv);
    }

    @Override
    public double getCv(int subId) {
        return mapper.getCv(subId);
    }

    @Override
    public int insertCoordinate(int subId, Point coordinate) {
        System.out.println(coordinate.toString());
        return mapper.insertCoordinate(subId, coordinate.toString());
    }

    @Override
    public int clearCoordinate(int subId) {
        return mapper.clearCoordinate(subId);
    }

    @Override
    public Coordinates getCoordinates(int subId) {//在这里进行转换
        //String coordinates=
        return mapper.getCoordinates(subId);
    }

    @Override
    public Point getCoordinate(int subId) {
        return mapper.getCoordinate(subId);
    }

    @Override
    public int setBathStatus(int subId, int status) {
        return mapper.setBathStatus(subId, status);
    }

    @Override
    public int getBathStatus(int subId) {
        return mapper.getBathStatus(subId);
    }
    @Override
    public int updateBath(Map map) {
        return mapper.updateBath(map);
    }

    @Override
    public Date getStartTime(int subId) {
        java.util.Date date=mapper.getStartTime(subId);
        if (date==null)
            return null;
        Date d=new Date(date.getTime());
        return d;
    }

    @Override
    public String getPoints(int subId) {
        return mapper.getPoints(subId);
    }

    @Override
    public boolean updatePoints(Map map) {
        if (mapper.updatePoints(map) == 1)
            return true;
        else
            return false;
    }

    @Override
    public Bath getBathInfo(int subId) {

        return mapper.getBathInfo(subId);
    }

    @Override
    public Map getBathSVAndST(int subId) {
        return mapper.getBathSVAndST(subId);
    }

    @Override
    public int changeBathStatus(int subId, int status) {//返回改变后的status
        //如果数据库里的status和传入的status相同则不作任何处理
        //最终结果以设备反馈的信息为准
        //获取设备当前状态
        int dStatus=mapper.getBathStatus(subId);
        if (dStatus==status)
        {
            return status;
        }
        if (mapper.setBathStatus(subId,status)==0)
            return -1;
        //更新过之后，通过mqttService发送数据给设备
        if (status==0)
        mqttService.closeDevice(subId);
        else if (status==1)
            mqttService.openDevice(subId);
        return 0;
    }

    @Override
    public int setSVandST(double ST, double SV,int subId) {
        return mapper.setSVandST(ST,SV,subId);
    }

}
