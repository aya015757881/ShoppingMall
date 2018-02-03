package com.hwua.biz;

import java.io.IOException;



import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hwua.bean.Product;
import com.hwua.util.DBHelper;

public class ProductsPreparation implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession(false);
		
		if(session==null||session.getAttribute("updateIndex")!=null){
			try {
				DBHelper.getConnected();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		
			session = req.getSession();
		
			ArrayList<Product> products = Getter.getAllProducts();
			ArrayList<Product> categoryProducts = Getter.getCategoryProducts(products,0);
			ArrayList<Product> pageProducts = Getter.getPageProducts(categoryProducts, 1);
			int pageList[] = Getter.getPageList(categoryProducts);
		
			session.setAttribute("products", products);
			session.setAttribute("categoryProducts", categoryProducts);
			session.setAttribute("pageProducts", pageProducts);
			session.setAttribute("categoryId", 0);
			session.setAttribute("currentPage", 1);
			session.setAttribute("pageList", pageList);
			session.setAttribute("pageCount", pageList.length);
		}
		
		session.removeAttribute("updateIndex");
		chain.doFilter(request, response);
	}
}
