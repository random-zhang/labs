package com.jie.dao;

import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import com.jie.bean.Point;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public interface BathMapper  extends DeviceMapper{
    int insert(Bath record);
    Bath getBath(Integer subId);
    int insertSelective(Bath record);
    int settingBath(Bath bath);
    int updateCv(@Param("subId") int subId,@Param("cv") double cv);
    double getCv(int subId);
   // int updateCoordinates(@Param("subId") int subId);
    int insertCoordinate(@Param("subId") int subId,@Param("coordinate")String coordinate);
    int clearCoordinate(@Param("subId") int subId);
    Coordinates  getCoordinates(int subId);
    Point getCoordinate(int subId);
    int setBathStatus(@Param("subId") int subId,@Param("status") int status);
    int getBathStatus(int subId);
    int updateBath(Map map);
    java.util.Date getStartTime(int subId);
    String getPoints(int subId);
    int updatePoints( Map map);
    Bath getBathInfo( int subId);
    HashMap findCurrentStatusAndUser(int subId);
    Map getBathSVAndST(int subId);
    Map getCoordinateAndStartTime(int subId);
    int setSVandST(@Param("st")double ST,@Param("sv") double SV,@Param("subId") int subId);
}