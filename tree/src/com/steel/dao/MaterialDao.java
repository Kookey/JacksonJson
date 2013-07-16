package com.steel.dao;

import java.sql.SQLException;
import java.util.List;

import com.steel.entity.Material;

public interface MaterialDao {

	public List<Material> findMaterialByPage(int pageId,int pageSize,String cateId) throws SQLException;
	public int findCount(String cateId) throws SQLException;
}
