package com.jie.service;

import com.jie.bean.PositionAndPoints;
import com.jie.bean.User;
import com.jie.bean.myDevice;
import com.jie.bean.myDeviceHistory;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    int insertUser(User user);

    User getUser(String id);

    User login(User user);

    User getUserByPhone(String userPhone);

    int register(User user);//返回注册后分配的id

    int getUserId(String userPhone);

    List<myDevice> getDevices(int userId);//找到对应用户的所有设备

    boolean bindDevice(Map map);

    boolean UnbundlingDevice(Map map);//解绑

    List<myDeviceHistory> selectMyhistoryDevices(int userId);

    List<Map> selectMyHistoryDeviceNameAndId(int subId);

    boolean updateDevicePosition(int userId, int subId);

    boolean updateMyDevices(int subId, int userId, String alias);

    int insertDevicePoints(int deviceId, int userId, String points);

    int deleteDevicePoints(int deviceId, int userId, int position);

    List<String> selectDevicePoints(int deviceId, int userId);

    List<PositionAndPoints> selectDevicePositionAndPoints(int deviceId, int userId);

    ArrayList<HashMap> getMyDevicesHistoryIdsBySub(int userId);

    String getSubNameById(int userId,int subId);
    HashMap getHistoryByHistoryId(int userId,int historyId);
    HashMap getMyManageDevice(int userId,int subId);
    int updateMyManageDevice(Map map);
    int deleteDeviceFromMyDevices(Map map);
    String bindDeviceByAuthorizedCode(Map map);
    boolean createDeviceHistory(int userId,int subId);
    List<Map>  getMyAlias(int userId);
    Map getSubHistoryIds(int userId,int subId);
    String getMyAlia(int userId,int subId);
}
