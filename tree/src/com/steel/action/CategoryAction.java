package com.steel.action;

import java.sql.SQLException;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.steel.service.CategoryService;
import com.steel.service.impl.CategoryServiceImpl;

public class CategoryAction extends ActionSupport{

	private String id;
	private String name;
	public CategoryService cateService;
	private String cateStr;
	private String root;
	public String getJsonString(){
		
		cateService = new CategoryServiceImpl();
		try {
			cateStr = cateService.getCateString(id,name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getCateStr() {
		return cateStr;
	}

	public void setCateStr(String cateStr) {
		this.cateStr = cateStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
