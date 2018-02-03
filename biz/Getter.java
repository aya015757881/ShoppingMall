package com.hwua.biz;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import com.hwua.bean.Product;
import com.hwua.dao.DBOperator;

public class Getter {
	
	public static ArrayList<Product> getAllProducts(){
		
		ArrayList<Product> products = new ArrayList<Product>();
		String sql = "select * from hwua_product order by sale_count desc";
		
		try {
			ResultSet rs = DBOperator.getResult(sql);
			while(rs.next()){
				Product p = new Product();
				p.setCatId(rs.getInt("hpc_id"));
				p.setCcatId(rs.getInt("hpc_child_id"));
				p.setDescription(rs.getString("hp_description"));
				p.setFileName(rs.getString("hp_file_name"));
				p.setId(rs.getInt("hp_id"));
				p.setName(rs.getString("hp_name"));
				p.setPrice(rs.getDouble("hp_price"));
				p.setStock(rs.getInt("hp_stock"));
				p.setSaleCount(rs.getInt("sale_count"));
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public static ArrayList<Product> getCategoryProducts(ArrayList<Product> products,int categoryId){
		
		if(categoryId==0)
			return products;
		
		ArrayList<Product> cproducts = new ArrayList<Product>();
		
		for(Product p : products)
			if(p.getCatId()==categoryId||p.getCcatId()==categoryId)
				cproducts.add(p);
		
		return cproducts;
	}
	
	public static ArrayList<Product> getPageProducts(ArrayList<Product> products,int page){
		
		int start = 12*(page-1);
		int end = start + 12;
		ArrayList<Product> pproducts = new ArrayList<Product>();
		
		for(int i=start;i<end;i++)
			if(i<products.size())
				pproducts.add(products.get(i));
		
		return pproducts;
	}
	
	public static ArrayList<Product> getFuzzyProducts(ArrayList<Product> products,String qname){
		
		ArrayList<Product> fp = new ArrayList<Product>();
		
		for(Product p : products){
			String name = p.getName();
			String cName = getCategoryName(p.getCatId());
			String ccName = getCategoryName(p.getCcatId());
			if(match(name,qname)||match(cName,qname)||match(ccName,qname))
				fp.add(p);
		}
		
		return fp;
	}
	
	public static int[] getPageList(ArrayList<Product> products){
		
		int count = products.size();
		int pageCount = count/12 + (count%12==0?0:1);
		int pageList[] = new int[pageCount];
		
		for(int i=0;i<pageCount;i++)
			pageList[i] = i+1;
		
		return pageList;
	}
	
	public static String getCategoryName(int categoryId){
		
		String sql = "select hpc_name from hwua_product_category where hpc_id = ?";
		String name = "";
		
		try {
			ResultSet rs = DBOperator.getResult(sql, categoryId);
			if(rs.next())
				name = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	private static boolean match(String sub,String tar){
		
		if(sub.contains(tar))
			return true;
		
		String str[] = new String[tar.length()];
		
		for(int i=0;i<tar.length();i++)
			str[i] = tar.substring(i,i+1);
		
		for(String s : str)
			if(sub.contains(s))
				return true;
		
		return false;
	}
	
	public static Product getProduct(ArrayList<Product> products,int id){
		
		for(Product p : products)
			if(p.getId()==id)
				return p;
		
		return null;
	}
}

