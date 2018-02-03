package com.hwua.biz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.ProductsBatch;

public class MoreThanInStock extends HttpServlet {

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
		PrintWriter out = resp.getWriter();
		AddToCart add = new AddToCart();
		
		if(shoppingList==null)
			out.write("0");
		else if(!add.hasProduct(shoppingList,pid))
			out.write("0");
		else{
			ProductsBatch pb = add.getProduct(shoppingList,pid);
			if(pb.getCount()+count>pb.getProduct().getStock())
				out.write("1");
			else
				out.write("0");
		}
	}
}





