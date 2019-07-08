package com.jie.dao;

import com.jie.bean.DHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface DHistoryMapper {
    int insert(DHistory record);
    int insertSelective(DHistory record);
}