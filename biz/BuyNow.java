package com.hwua.biz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.ProductsBatch;

public class BuyNow extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pidStr = req.getParameter("pid");
		String countStr = req.getParameter("count");
		int pid = Integer.parseInt(pidStr);
		int count = Integer.parseInt(countStr);
		HttpSession session = req.getSession(false);
		ArrayList<ProductsBatch> shoppingList = (ArrayList<ProductsBatch>)session.getAttribute("shoppingList");
		AddToCart add = new AddToCart();
		
		if(shoppingList==null){
			shoppingList = new ArrayList<ProductsBatch>();
			add.addProduct(session,shoppingList,pid,count);
			session.setAttribute("shoppingList", shoppingList);
		}else if(!add.hasProduct(shoppingList,pid))
			add.addProduct(session,shoppingList,pid,count);
		else{
			ProductsBatch pb = add.getProduct(shoppingList,pid);
			pb.setCount(pb.getCount()+count);
		}
		
		resp.sendRedirect("shopping.jsp");
	}
}
