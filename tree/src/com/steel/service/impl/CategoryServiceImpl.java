package com.steel.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.steel.dao.CategoryDao;
import com.steel.dao.impl.CategoryDaoImpl;
import com.steel.entity.Category;
import com.steel.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	public String getCateString(String id,String name) throws SQLException {
		CategoryDao CateDao = new CategoryDaoImpl();
		List<Category> list = CateDao.findAllCategory(id,name);
		StringBuffer json = new StringBuffer("[");
		String data = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Category cate = list.get(i);
				json.append("{id:\""+cate.getCateId()+"\",");
				json.append("pid:\""+cate.getPid()+"\",");
				json.append("name:\""+cate.getName()+"\",");
				if(cate.getIsParent()==1){
					 json.append("isParent:"+cate.getIsParent()+",");  
				}
				 data=json.substring(0,json.lastIndexOf(","))+"},";  
				 json=new StringBuffer(data);  

			}
			data=json.substring(0, json.length()-1)+"]";  
		} else {
			System.out.println("读取数据失败");
		}
		return data;
	}
	public String getRootString(String root) throws SQLException {
		CategoryDao CateDao = new CategoryDaoImpl();
		List<Category> list = CateDao.findRootCategoryByIsParent(root);
		StringBuffer json = new StringBuffer("[");
		String data = "";
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				Category cate = list.get(i);
				json.append("{id:\""+cate.getCateId()+"\",");
				json.append("pid:\""+cate.getPid()+"\",");
				json.append("name:\""+cate.getName()+"\",");
				if(cate.getIsParent()==1){
					 json.append("isParent:"+cate.getIsParent()+",");  
				}
				 data=json.substring(0,json.lastIndexOf(","))+"},";  
				 json=new StringBuffer(data);  

			}
			data=json.substring(0, json.length()-1)+"]";
		} else {
			System.out.println("读取数据失败");
		}
		return data;
	}
}
