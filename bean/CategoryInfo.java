package com.hwua.bean;

import java.util.ArrayList;

public class CategoryInfo {
	
	private Category bigCat;
	private ArrayList<Category> tinyCats;
	
	public Category getBigCat() {
		return bigCat;
	}
	public void setBigCat(Category bigCat) {
		this.bigCat = bigCat;
	}
	public ArrayList<Category> getTinyCats() {
		return tinyCats;
	}
	public void setTinyCats(ArrayList<Category> tinyCats) {
		this.tinyCats = tinyCats;
	}
}
