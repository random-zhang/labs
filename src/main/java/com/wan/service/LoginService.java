package com.wan.service;
import com.wan.dto.LoginDto;
import com.wan.exception.ServiceException;

public interface LoginService {
	LoginDto getUser(String employeeId, String password)throws ServiceException;
	int updatePassword(String employeeId,String newpassword) throws ServiceException;
}
