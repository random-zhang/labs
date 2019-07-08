package com.wan.transaction.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.wan.exception.ServiceException;
import com.wan.transaction.TransManagerI;
import com.wan.util.JDBCUtil;

/**
 * 事务管理的实现类
 *
 */
public class TransManagerImpl implements TransManagerI {

	@Override
	public void beginTrans() throws ServiceException {
		try {
			Connection con = JDBCUtil.getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("开启事务失败");
		}
	}

	@Override
	public void commitTrans() throws ServiceException {
		Connection con = null;
		try {
			con = JDBCUtil.getConnection();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("提交事务失败");
		} finally {
			JDBCUtil.close(con, null, null);
		}
	}

	@Override
	public void rollbackTrans() throws ServiceException {
		Connection con = null;
		try {
			con = JDBCUtil.getConnection();
			con.rollback();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("回滚事务失败");
		} finally {
			JDBCUtil.close(con, null, null);
		}
	}

}
