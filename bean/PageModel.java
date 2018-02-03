package com.hwua.bean;

import java.util.ArrayList;

public class PageModel {
	
	private int pageCount;
	private int pageList[];
	private int categoryId;
	private ArrayList<Product> products;
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int[] getPageList() {
		return pageList;
	}
	public void setPageList(int[] pageList) {
		this.pageList = pageList;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
}
