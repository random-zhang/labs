package com.jie.dao;

import com.wan.dto.LoginDto;
import com.wan.exception.DaoException;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDaoI {
	LoginDto  selectUser(@Param("employeeId") Integer  employeeId , @Param("password")  String password) ;
	int updatePassword(@Param("employeeId") Integer employeeId , @Param("password")String password);
}
