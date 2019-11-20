package com.jie.service;

import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import com.jie.bean.Point;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.Map;

public interface bathService {
    Bath  getBath(Integer subId);
    int  settingBath(Bath bath);
    int updateCv(int subId,double cv);
    double getCv(int subId);
    int insertCoordinate(int subId, Point coordinate);
    int clearCoordinate(int subId);
    Coordinates getCoordinates(int subId);
   Point  getCoordinate(int subId);
    int setBathStatus(int subId,int status);
    int getBathStatus(int subId);
    int updateBath(Map map);
    Date getStartTime(int subId);
    String getPoints(int subId);
    boolean updatePoints( Map map);
    Bath getBathInfo( int subId);
    Map getBathSVAndST(int subId);
    int changeBathStatus(int subId,int status);
    int setSVandST(double ST,double SV,int subId);
}
