package com.hwua.biz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.ProductsBatch;

public class DeleteCart extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pidStr = req.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		HttpSession session = req.getSession(false);
		ArrayList<ProductsBatch> shoppingList = (ArrayList<ProductsBatch>)session.getAttribute("shoppingList");
		
		new AddToCart().removeProduct(shoppingList, pid);
		resp.sendRedirect("shopping.jsp");
	}
}
