package com.wan.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wan.model.mapper.RowMapper;



public class JDBCTemplate {
	
	public Object save(String sql,Object... params) throws DataAccessException{
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		Object pk=null;
		try {
			//获得Connection
			conn=JDBCUtil.getConnection();
			ps=conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		    for(int i=0;i<params.length;i++){
		    	ps.setObject(i+1,params[i]);
		    }
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				pk = rs.getObject(1);
			}
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new DataAccessException("数据保存异常");
		}
		finally {
			//释放资源ResultSet, Statement,Connection
			JDBCUtil.close(null, ps, rs);
		}				
		return pk;
	}
	
	public void update(String sql,Object... params) throws DataAccessException{
		Connection conn = null;
		PreparedStatement ps=null;
		try {
			//获得Connection
			conn=JDBCUtil.getConnection();
			ps=conn.prepareStatement(sql);
		    for(int i=0;i<params.length;i++){
		    	ps.setObject(i+1,params[i]);
		    }
			ps.executeUpdate();			
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new DataAccessException("数据更新异常");
		}
		finally {
			//释放资源ResultSet, Statement,Connection
			JDBCUtil.close(null, ps, null);
		}				
	
	}
	
	public void delete(String sql,Object... params) throws DataAccessException{
		Connection conn = null;
		PreparedStatement ps=null;
		try {
			//获得Connection
			conn=JDBCUtil.getConnection();
			ps=conn.prepareStatement(sql);
		    for(int i=0;i<params.length;i++){
		    	ps.setObject(i+1,params[i]);
		    }
			ps.executeUpdate();			
		} 
		catch(SQLException e){
			e.printStackTrace();
			throw new DataAccessException("数据删除异常");
		}
		finally {
			//释放资源ResultSet, Statement,Connection
			JDBCUtil.close(null, ps, null);
		}				
	
	}
	
	public List query(String sql,RowMapper rm,Object... params) throws DataAccessException{
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
	    List list = new ArrayList();
		try {
			//获得Connection
			conn=JDBCUtil.getConnection();
			ps=conn.prepareStatement(sql);
		    for(int i=0;i<params.length;i++){
		    	ps.setObject(i+1,params[i]);
		    }
		    System.out.println(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Object o=rm.mapRow(rs);
				list.add(o);
			}
			
		} 
		catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("数据查询异常");
		}
		finally {
			//释放资源ResultSet, Statement,Connection
			JDBCUtil.close(null, ps, rs);
		}				
	    return list;
	}
	

}
