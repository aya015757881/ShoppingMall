package com.hwua.biz;

import java.io.IOException;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.hwua.dao.DBOperator;
import com.hwua.bean.Category;
import com.hwua.bean.CategoryInfo;

public class CategoryView implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request.getAttribute("categoryInfo")==null)
			request.setAttribute("categoryInfo", getList());
		chain.doFilter(request, response);
	}
	
	private ArrayList<CategoryInfo> getList(){
		
		ArrayList<CategoryInfo> list = getInfoList();
		
		for(CategoryInfo info : list){
			Category bigCat = info.getBigCat();
			ArrayList<Category> tinyCats = getTinyCats(bigCat);
			info.setTinyCats(tinyCats);
		}
		
		return list;
	}
	
	private ArrayList<CategoryInfo> getInfoList(){
		
		ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
		
		try {
			ResultSet rs = DBOperator.getResult("select * from hwua_product_category where hpc_id = hpc_parent_id"
					+ " and hpc_name != 'Ò×ÂòÉçÇø' ");
			while(rs.next()){
				CategoryInfo categoryInfo = new CategoryInfo();
				Category cat = new Category();
				cat.setName(rs.getString("hpc_name"));
				cat.setId(rs.getInt("hpc_id"));
				cat.setParentId(rs.getInt("hpc_parent_id"));
				categoryInfo.setBigCat(cat);
				list.add(categoryInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private ArrayList<Category> getTinyCats(Category bigCat){
		
		int bigCatId = bigCat.getId();
		
		ArrayList<Category> tinyCats = new ArrayList<Category>();
		
		try {
			ResultSet rs = DBOperator.getResult("select * from hwua_product_category where hpc_parent_id "
					+ "!= hpc_id and hpc_parent_id = ?", bigCatId);
			while(rs.next()){
				Category cat = new Category();
				cat.setName(rs.getString("hpc_name"));
				cat.setId(rs.getInt("hpc_id"));
				cat.setParentId(rs.getInt("hpc_parent_id"));
				tinyCats.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tinyCats;
	}
}
