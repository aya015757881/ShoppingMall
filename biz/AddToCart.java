package com.hwua.biz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.Product;
import com.hwua.bean.ProductsBatch;

public class AddToCart extends HttpServlet {

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
		
		if(shoppingList==null){
			shoppingList = new ArrayList<ProductsBatch>();
			addProduct(session,shoppingList,pid,count);
			session.setAttribute("shoppingList", shoppingList);
			out.write("0");
		}else if(hasProduct(shoppingList,pid)){
			ProductsBatch pb = getProduct(shoppingList,pid);
			if(pb.getCount()+count>pb.getProduct().getStock())
				out.write("1");
			else{
				pb.setCount(pb.getCount()+count);
				out.write("0");
			}
		}else{
			addProduct(session,shoppingList,pid,count);
			out.write("0");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addProduct(HttpSession session,ArrayList<ProductsBatch> pb,int pid,int count){
		
		ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("products");
		Product p = Getter.getProduct(products, pid);
		pb.add(new ProductsBatch(p,count));
	}
	
	public boolean hasProduct(ArrayList<ProductsBatch> pb,int pid){
		
		for(ProductsBatch p : pb)
			if(p.getProduct().getId()==pid)
				return true;
		
		return false;
	}
	
	public ProductsBatch getProduct(ArrayList<ProductsBatch> pb,int pid){
		
		for(ProductsBatch p : pb)
			if(p.getProduct().getId()==pid)
				return p;
		
		return null;
	}
	
	public void removeProduct(ArrayList<ProductsBatch> pb,int pid){
		
		ProductsBatch pBatch = null;
		
		for(ProductsBatch p : pb)
			if(p.getProduct().getId()==pid)
				pBatch = p;
		
		pb.remove(pBatch);
	}
}

