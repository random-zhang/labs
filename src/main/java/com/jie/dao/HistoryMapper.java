package com.jie.dao;

import com.jie.bean.History;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryMapper {
    int insert(History record);

    int insertSelective(History record);
    int insertDevicePoints(@Param("deviceId") int deviceId,@Param("userID") int userId,@Param("points") String points);
}