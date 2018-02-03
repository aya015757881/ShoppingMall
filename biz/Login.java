package com.hwua.biz;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hwua.bean.User;
import com.hwua.dao.DBOperator;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		
		String username = req.getParameter("userName");
		String password = req.getParameter("passWord");
		
		HttpSession session = req.getSession();
		
		try {
			String sql1 = "select hu_user_id from hwua_user where hu_user_name = ?";
			String sql2 = "select hu_user_id from hwua_user where hu_user_name = ? and hu_password = ?";
			if(!DBOperator.hasResult(sql1, username)){
				session.setAttribute("loginStatus", "noUser");
				resp.sendRedirect("login.jsp");
			}else if(!DBOperator.hasResult(sql2, username, password)){
				session.setAttribute("loginStatus", "wrongPassword");
				resp.sendRedirect("login.jsp");
			}else{
				session.setAttribute("user", new User(username));
				resp.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
