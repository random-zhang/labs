package com.wan.model.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
	
	public T mapRow(ResultSet rs) throws Exception;

}
