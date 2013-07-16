package com.steel.service;

import java.sql.SQLException;

public interface CategoryService {

	public String getCateString(String id,String name) throws SQLException;
	public String getRootString(String root) throws SQLException;
	
}
