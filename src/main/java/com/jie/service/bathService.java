package com.jie.service;

import com.jie.bean.Bath;
import com.jie.bean.Coordinates;

public interface bathService extends deviceService {
    Bath  getBath(Integer subId);
    int  settingBath(Bath bath);
    int updateCv(int subId,double cv);
    double getCv(int subId);
    int insertCoordinate(int subId, Coordinates.Point coordinate);
    int clearCoordinate(int subId);
    Coordinates getCoordinates(int subId);
    Coordinates.Point  getCoordinate(int subId);
    int setBathStatus(int subId,int status);
    int getBathStatus(int subId);
}
