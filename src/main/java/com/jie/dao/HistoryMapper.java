package com.jie.dao;

import com.jie.bean.History;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryMapper {
    int insert(History record);

    int insertSelective(History record);
}