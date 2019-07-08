package com.jie.dao;

import com.jie.bean.Bath;
import com.jie.bean.Coordinates;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BathMapper {
    int insert(Bath record);
    Bath getBath(Integer subId);
    int insertSelective(Bath record);
    int settingBath(Bath bath);
    int updateCv(@Param("subId") int subId,@Param("cv") double cv);
    double getCv(int subId);
    int updateCoordinates();
    int insertCoordinate(@Param("subId") int subId,@Param("coordinate")String coordinate);
    int clearCoordinate(@Param("subId") int subId);
    Coordinates  getCoordinates(int subId);
    Coordinates.Point  getCoordinate(int subId);
    int setBathStatus(@Param("subId") int subId,@Param("status") int status);
    int getBathStatus(int subId);
}