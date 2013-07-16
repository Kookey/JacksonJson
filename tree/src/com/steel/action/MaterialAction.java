package com.steel.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.steel.entity.Material;
import com.steel.service.MaterialService;
import com.steel.service.impl.MaterialServiceImpl;

public class MaterialAction extends ActionSupport {

	private int pageIndex;
	private int pageSize;
	private int totalPage;
	private int rowCount;
	private String cateId;
	private List<Material> lists = new ArrayList<Material>();

	public String getMaterials() {
		MaterialService mateService = new MaterialServiceImpl();
		try {
			rowCount = mateService.getCount(cateId);
			pageIndex = pageIndex*pageSize;
			lists = mateService.getMaterialByPage(pageIndex, pageSize,cateId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<Material> getLists() {
		return lists;
	}
	public void setLists(List<Material> lists) {
		this.lists = lists;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
}
