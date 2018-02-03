package com.hwua.bean;

import java.util.ArrayList;

public class Order {
	
	private String createTime;
	private String id;
	private double cost;
	private ArrayList<ProductsBatch> shoppingList;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public ArrayList<ProductsBatch> getShoppingList() {
		return shoppingList;
	}
	public void setShoppingList(ArrayList<ProductsBatch> shoppingList) {
		this.shoppingList = shoppingList;
	}
}
