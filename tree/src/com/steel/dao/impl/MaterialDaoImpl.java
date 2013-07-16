package com.steel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.steel.dao.MaterialDao;
import com.steel.entity.Material;
import com.steel.util.DBUtil;

public class MaterialDaoImpl implements MaterialDao {

	private Connection conn;

	public int findCount(String cateId) throws SQLException {
		conn = DBUtil.getConnection();
		int rowCount = 0;
		String sql = "select count(*) from esb_bas_material where 1=1";
		if(!(cateId.equals("null"))){
			sql+=" and l3_code = "+"'"+cateId+"'";
		}
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		if (rs.next()) {
			rowCount = rs.getInt(1);
		}
		DBUtil.close(conn, ps, rs);
		return rowCount;
	}

	public List<Material> findMaterialByPage(int pageIndex,int pageSize,String cateId)
			throws SQLException {
		List<Material> list = new ArrayList<Material>();
		conn = DBUtil.getConnection();
		String sql = "select * from esb_bas_material where 1=1";
		if(!(cateId.equals("null"))){
			sql+=" and l3_code = "+"'"+cateId+"'";
		}
		sql+=" limit ?,?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, pageIndex);
		ps.setInt(2, pageSize);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Material mate = new Material();
			mate.setId(rs.getString("ID"));
			mate.setCode(rs.getString("CODE"));
			mate.setName(rs.getString("NAME"));
			mate.setUnitName(rs.getString("UNIT_NAME"));
			mate.setMaterialQuaity(rs.getString("MATERIAL_QUALITY"));
			mate.setShortStandardName(rs.getString("SHORT_STANDARD_NAME"));
			mate.setStandardModel(rs.getString("STANDARD_MODEL"));
			mate.setLe3Code(rs.getString("L3_CODE"));
			mate.setLe3Name(rs.getString("L3_NAME"));
			mate.setLe2Code(rs.getString("L2_CODE"));
			mate.setLe2Name(rs.getString("L2_NAME"));
			mate.setLe1Code(rs.getString("L1_CODE"));
			mate.setLe1Name(rs.getString("L1_NAME"));
			mate.setField(rs.getString("FIELD"));
			mate.setSourceSystemCode(rs.getString("SOURCE_SYSTEM_CODE"));
			mate.setNameCode(rs.getString("NAME_CODE"));
			mate.setMaterialQualityCode(rs.getString("MATERIAL_QUALITY_CODE"));
			mate.setStandardModelCode(rs.getString("STANDARD_MODEL_CODE"));
			mate.setFieldCode(rs.getString("FIELD_CODE"));
			list.add(mate);
		}
		DBUtil.close(conn, ps, rs);
		return list;
	}
}
