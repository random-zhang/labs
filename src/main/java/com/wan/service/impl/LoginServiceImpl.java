package com.wan.service.impl;

import com.jie.dao.LoginDaoI;
import com.wan.dto.LoginDto;
import com.wan.exception.DaoException;
import com.wan.exception.ServiceException;
import com.wan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
    LoginDaoI loginDaoI =null;
	LoginServiceImpl(){
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
	public  LoginDto getUser(String employeeId, String password)
			throws ServiceException {
		LoginDto  dto = null;
		try {
			Integer id=Integer.parseInt(employeeId);
			dto = loginDaoI.selectUser(id, password);
		} catch (DaoException e) {
			throw new ServiceException("查询异常");
		}
		return dto;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
	public int updatePassword(String employeeId, String newpassword)
			throws ServiceException {
	    int row=0;
		try {
            Integer id=Integer.parseInt(employeeId);
			row=loginDaoI.updatePassword(id, newpassword);
		} catch (DaoException e) {
			throw new ServiceException("修改密码异常");
		}
		return  row;
	}

}
