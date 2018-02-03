package com.hwua.biz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hwua.bean.Product;

public class ViewedProducts implements Filter {

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		
		String pIdStr = req.getParameter("pId");
		int id = Integer.parseInt(pIdStr);
		Product p = null;
		ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("products");
		
		for(Product pro : products)
			if(pro.getId()==id){
				p = pro;
				break;
			}
		
		LinkedList<Product> viewedProducts = (LinkedList<Product>)session.getAttribute("viewedProducts");
		
		if(viewedProducts==null)
			viewedProducts = new LinkedList<Product>();
		
		add(viewedProducts,p);
		
		session.setAttribute("viewedProducts", viewedProducts);
		
		chain.doFilter(request, response);
	}
	
	private void add(LinkedList<Product> viewedProducts,Product p){
		
		if(viewedProducts.size()==0||viewedProducts.get(0)!=p){
			
			viewedProducts.addFirst(p);
			
			if(viewedProducts.size()>4)
				viewedProducts.removeLast();
		}
	}
}
