package com.hwua.biz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.ProductsBatch;

public class AlterQuantity extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operation = req.getParameter("operation");
		String pidStr = req.getParameter("pid");
		int pid = Integer.parseInt(pidStr);
		AddToCart add = new AddToCart();
		HttpSession session = req.getSession(false);
		ArrayList<ProductsBatch> shoppingList = (ArrayList<ProductsBatch>)session.getAttribute("shoppingList");
		
		ProductsBatch pb = add.getProduct(shoppingList,pid);
		if("plus".equals(operation))
			pb.setCount(pb.getCount()+1);
		else if("minus".equals(operation))
			pb.setCount(pb.getCount()-1);
		else{
			int count = Integer.parseInt(operation);
			pb.setCount(count);
		}
	}
}
