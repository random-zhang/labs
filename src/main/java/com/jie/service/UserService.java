package com.jie.service;

import com.jie.bean.User;

public interface UserService {
     int insertUser(User user);
    User getUser(String id);
    User login(User user);
    User getUserByPhone(String userPhone);
    int register(User user);//返回注册后分配的id
    int getUserId(String userPhone);
}
