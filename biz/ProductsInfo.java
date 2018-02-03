package com.hwua.biz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.Category;
import com.hwua.bean.Product;
import com.hwua.dao.DBOperator;

public class ProductsInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pIdStr = req.getParameter("pId");
		int id = Integer.parseInt(pIdStr);
		
		HttpSession session = req.getSession(false);
		ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("products");
		Product p = null;
		
		for(Product pro : products)
			if(pro.getId()==id){
				p = pro;
				break;
			}
		
		req.setAttribute("product", p);
		
		Category c[] = new Category[2];
		c[0] = getCategory(p.getCatId());
		c[1] = getCategory(p.getCcatId());
		
		req.setAttribute("category", c);
		
		req.getRequestDispatcher("product_view.jsp").forward(req, resp);
	}
	
	private Category getCategory(int id){
		
		String sql = "select * from hwua_product_category where hpc_id = ?";
		Category c = new Category();
		
		try {
			ResultSet rs = DBOperator.getResult(sql, id);
			rs.next();
			c.setId(rs.getInt("hpc_id"));
			c.setName(rs.getString("hpc_name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
}



