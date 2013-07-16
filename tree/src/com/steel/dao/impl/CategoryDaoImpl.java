package com.steel.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.steel.dao.CategoryDao;
import com.steel.entity.Category;
import com.steel.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

	private Connection conn;

	public List<Category> findAllCategory(String id, String name)
			throws SQLException {
		List<Category> list = new ArrayList<Category>();
		conn = DBUtil.getConnection();
		String sql = "select * from category where pid='" + id + "'";
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			Category cate = new Category();
			cate.setCateId(rs.getString("id"));
			cate.setName(rs.getString("name"));
			cate.setPid(rs.getString("pid"));
			cate.setIsParent(rs.getInt("isParent"));
			list.add(cate);
		}
		DBUtil.close(conn, ps, rs);
		return list;
	}

	public List<Category> findRootCategoryByIsParent(String root)
			throws SQLException {
		List<Category> list = new ArrayList<Category>();
		conn = DBUtil.getConnection();
		String sql = "select * from category where pid='" + root + "'";
		Statement ps = conn.createStatement();
		ResultSet rs = ps.executeQuery(sql);
		while (rs.next()) {
			Category cate = new Category();
			cate.setCateId(rs.getString("id"));
			cate.setName(rs.getString("name"));
			cate.setPid(rs.getString("pid"));
			cate.setIsParent(rs.getInt("isParent"));
			list.add(cate);
		}
		DBUtil.close(conn, ps, rs);
		return list;
	}

}
