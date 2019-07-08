package com.jie.dao;

import com.jie.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int insert(User record);
    User getUser(String id);
    User getUserByPhone(String userPhone);
    int getUserId(String userPhone);
}