package com.jie.dao;

import com.jie.bean.BathHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface BathHistoryMapper {
    int insert(BathHistory record);
    int insertSelective(BathHistory record);
}