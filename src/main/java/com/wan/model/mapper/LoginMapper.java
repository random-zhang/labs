package com.wan.model.mapper;

import java.sql.ResultSet;

import com.wan.dto.LoginDto;

public class LoginMapper implements RowMapper<LoginDto> {

	@Override
	public LoginDto mapRow(ResultSet rs) throws Exception {
		return new LoginDto(rs.getInt("USER_ID"),
				   rs.getString("EMM_PASSWORD"),
				   rs.getInt("EMPLOYEE_ID"),
				   rs.getString("EMPLOYEE_NAME"),
				   rs.getInt("DEPARTMENT_ID"),
				   rs.getInt("POSITON_ID"),
				   rs.getString("PHONE"),
				   rs.getString("EMAIL"),
				   rs.getString("STATUS"),
				   rs.getInt("PARENT_EMPLOYEE_ID"),
				   rs.getDate("CREATE_TIME"),
		           rs.getDate("UPDATE_TIME"));
	}

}

