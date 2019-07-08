package com.jie.dao;

import com.jie.bean.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {
    int insert(Manager record);

    int insertSelective(Manager record);
}