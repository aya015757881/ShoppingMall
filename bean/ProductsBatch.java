package com.hwua.bean;

public class ProductsBatch {
	
	private Product product;
	private int count;
	
	public ProductsBatch(Product product, int count) {
		this.product = product;
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
