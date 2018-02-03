package com.hwua.biz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.Product;

public class ProductsView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession(false);
		
		String pageStr = req.getParameter("page");
		String categoryIdStr = req.getParameter("categoryId");
		String qname = req.getParameter("qname");
		ArrayList<Product> products;
		ArrayList<Product> categoryProducts;
		ArrayList<Product> pageProducts;
		
		if(pageStr!=null){
			int page = Integer.parseInt(pageStr);
			int prevPage = (int)session.getAttribute("currentPage");
			
			if(page!=prevPage){
				categoryProducts = (ArrayList<Product>)session.getAttribute("categoryProducts");
				pageProducts = Getter.getPageProducts(categoryProducts, page);
				updatePageProducts(session,pageProducts,page);
			}
		}else if(categoryIdStr!=null){
			int categoryId = Integer.parseInt(categoryIdStr);
			int prevCatId = (int) session.getAttribute("categoryId");
				
			if(categoryId!=prevCatId){
				products = (ArrayList<Product>)session.getAttribute("products");
				categoryProducts = Getter.getCategoryProducts(products, categoryId);
				updateCategoryProducts(session,categoryProducts,categoryId);
			}else if((int)session.getAttribute("currentPage")!=1){
				categoryProducts = (ArrayList<Product>)session.getAttribute("categoryProducts");
				pageProducts = Getter.getPageProducts(categoryProducts, 1);
				updatePageProducts(session,pageProducts,1);
			}
		}else{
			products = (ArrayList<Product>)session.getAttribute("products");
			categoryProducts = Getter.getFuzzyProducts(products, qname);
			updateCategoryProducts(session,categoryProducts,-1);
		}
		
		resp.sendRedirect("index.jsp");
	}
	
	private void updateCategoryProducts(HttpSession session,ArrayList<Product> products,int categoryId){
		
		ArrayList<Product> pageProducts;
		int pageList[];
		
		session.setAttribute("categoryProducts", products);
		session.setAttribute("categoryId", categoryId);
		pageProducts = Getter.getPageProducts(products, 1);
		pageList = Getter.getPageList(products);
		session.setAttribute("pageList", pageList);
		session.setAttribute("pageCount", pageList.length);
		updatePageProducts(session,pageProducts,1);
	}
	
	private void updatePageProducts(HttpSession session,ArrayList<Product> products,int currentPage){
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("pageProducts", products);
	}
}
