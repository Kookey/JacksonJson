package com.steel.service;

import java.sql.SQLException;
import java.util.List;

import com.steel.entity.Material;

public interface MaterialService {

	public List<Material> getMaterialByPage(int pageId,int pageSize,String cateId) throws SQLException;
	public int getCount(String cateId) throws SQLException;
}
