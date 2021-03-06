package com.hwua.biz;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.dao.DBOperator;

public class CheckEmail extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("email");
		
		try {
			String sql = "select hu_email from hwua_user where hu_email = ?";
			if(DBOperator.hasResult(sql, email))
				out.write("1");
			else
				out.write("0");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
