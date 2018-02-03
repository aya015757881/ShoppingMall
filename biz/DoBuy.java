package com.hwua.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.Order;
import com.hwua.bean.Product;
import com.hwua.bean.ProductsBatch;
import com.hwua.dao.DBOperator;

public class DoBuy extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int cost = 0;
		Random rand = new Random();
		Order order = new Order();
		HttpSession session = req.getSession(false);
		ArrayList<ProductsBatch> shoppingList = (ArrayList<ProductsBatch>) session.getAttribute("shoppingList");
		
		session.removeAttribute("shoppingList");
		
		if(shoppingList == null || shoppingList.size() == 0){
			resp.sendRedirect("shopping.jsp");
			return;
		}
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		
		for(ProductsBatch pb : shoppingList)
			cost += pb.getCount() * (pb.getProduct().getPrice());
		
		order.setCreateTime(year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second);
		order.setId(""+151192+rand.nextInt(9999999));
		order.setCost(cost);
		order.setShoppingList(shoppingList);
		
		session.setAttribute("order", order);
		session.setAttribute("updateIndex", " ");
		updateDB(shoppingList);
		resp.sendRedirect("shopping-result.jsp");
	}
	
	private void updateDB(ArrayList<ProductsBatch> shoppingList){
		
		for(ProductsBatch pb : shoppingList){
			Product p = pb.getProduct();
			int count = pb.getCount();
			p.setSaleCount(p.getSaleCount()+count);
			p.setStock(p.getStock()-count);
			alterDBInfo(p);
		}
	}
	
	private void alterDBInfo(Product p){
		
		String sql = "update hwua_product set sale_count = ?, hp_stock = ? where hp_id = ?";
		
		try {
			DBOperator.update(sql, p.getSaleCount(), p.getStock(), p.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

