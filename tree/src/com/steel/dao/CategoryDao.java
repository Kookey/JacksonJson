package com.steel.dao;

import java.sql.SQLException;
import java.util.List;

import com.steel.entity.Category;

public interface CategoryDao {
	
	public List<Category> findAllCategory(String id,String name) throws SQLException;
	public List<Category> findRootCategoryByIsParent(String root) throws SQLException;
	
	
}
