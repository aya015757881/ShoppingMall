package com.hwua.biz;

import java.io.IOException;


import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.dao.DBOperator;

public class Register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("userName");
		String password = req.getParameter("passWord");
		String sex = req.getParameter("sex");
		String birthday = req.getParameter("birthday");
		String identity = req.getParameter("identity");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String address = req.getParameter("address");
		
		try {
			DBOperator.update("insert into hwua_user values(seq_user.nextval,?,?,?,?,?,?,?,?,1)",
					username,password,sex,birthday,identity,email,mobile,address);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("doneRegister.jsp");
	}
}
