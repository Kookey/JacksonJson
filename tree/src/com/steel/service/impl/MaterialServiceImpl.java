package com.steel.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.steel.dao.MaterialDao;
import com.steel.dao.impl.MaterialDaoImpl;
import com.steel.entity.Material;
import com.steel.service.MaterialService;

public class MaterialServiceImpl implements MaterialService {

	public List<Material> getMaterialByPage(int pageIndex,int pageSize,String cateId) throws SQLException {
		MaterialDao mateDao = new MaterialDaoImpl();
		List<Material> list = mateDao.findMaterialByPage(pageIndex, pageSize,cateId);
		return list;
	}

	public int getCount(String cateId) throws SQLException {
		MaterialDao dao = new MaterialDaoImpl();
		int rowCount = dao.findCount(cateId);
		return rowCount;
	}
}
