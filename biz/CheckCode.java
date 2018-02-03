package com.hwua.biz;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCode extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		HttpSession session = req.getSession();
		
		String code = (String)session.getAttribute("validateCode");
		
		String code1 = req.getParameter("validateCode");
		
		if(code.equals(code1))
			out.write("1");
		else
			out.write("0");
	}
}
