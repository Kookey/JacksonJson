package com.steel.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Company {

	private int id;
	private double price;
	private String company;
	private Date date;
	private String size;
	private byte visible;
	public Company() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public byte getVisible() {
		return visible;
	}
	public void setVisible(byte visible) {
		this.visible = visible;
	}
	
}
