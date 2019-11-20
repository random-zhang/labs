package com.jie.dao;

import com.jie.bean.PositionAndPoints;
import com.jie.bean.User;
import com.jie.bean.myDevice;
import com.jie.bean.myDeviceHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int insert(User record);
    User getUser(String id);
    User getUserByPhone(String userPhone);
    int getUserId(String userPhone);
    List<myDevice> getDevices(@Param("userId")int userId);
    int bindDevice(Map map);
    int updateMyDevices(@Param("subId")int subId,@Param("userId")int userId,@Param("alias")String alias);
    int UnbundlingDevice(Map map);
    List<myDeviceHistory> selectMyhistoryDevices(int subId);
    List<Map>  selectMyHistoryDeviceNameAndId(int subId);
    int updateDevicePosition(@Param("userId") int userId,@Param("subId")int subId);
    int getDevicePositionInMyDevices(@Param("userId") int userId,@Param("subId")int subId);
   int   updateDevicePositionInMyDevices(@Param("userId") int userId,@Param("subId")Integer subId,@Param("position") Integer position);
    int insertDevicePoints(@Param("deviceId") int deviceId,@Param("userId") int userId,@Param("points") String points);
    int deleteDevicePoints(@Param("deviceId") int deviceId,@Param("userId") int userId,@Param("position") int position);
    int fixDevicePointsPosition(@Param("deviceId") int deviceId,@Param("userId") int userId,@Param("position") int position);
    List<String> selectDevicePoints(@Param("deviceId") int deviceId,@Param("userId") int userId);
    List<PositionAndPoints> selectDevicePositionAndPoints(@Param("deviceId") int deviceId, @Param("userId") int userId);
    ArrayList<HashMap> getMyDeviceHistoryIds(int userId);
    String getSubNameById(@Param("userId") int userId,@Param("subId") int subId);
    int getDeviceIdByHistoryId(@Param("userId") int userId,@Param("historyId") int historyId);
    HashMap getBathHistoryByHistoryId(int historyId);
    HashMap getMyManageDevice(@Param("userId") int userId,@Param("subId") int subId);
    int updateMyManageDevice(Map map);
    int deleteDeviceFromMyDevices(@Param("userId") int userId,@Param("subId") int subId);
    int updatePositionFromMyDevices(@Param("userId") int userId,@Param("subId") int subId);
    int getNumberFromMyDevices(int userId);
    int addManager(Map map);
    Integer getSubIdFromManagerByauthorizedCode(String authorizedCode);
    Integer insertMyDeviceHistory(myDeviceHistory mDeviceHistory);
    Integer insertMyBathHistory(@Param("historyId") int historyId, @Param("startTime")String startTime,@Param("endTime") String endTime,@Param("points") String points);
    List getMyAlias(int userId);
    String getMyAlia(@Param("userId") int userId,@Param("subId") int subId);
    List getSubHistoryIds(@Param("userId") int userId,@Param("subId") int subId);
}