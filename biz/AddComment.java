package com.hwua.biz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.dao.DBOperator;

public class AddComment extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		
		String time = year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
		
		String sql = "insert into hwua_comment(hc_id,hc_nick_name,hc_content,hc_create_time) values(seq_comment.nextval,?,?,?)";
		
		try {
			DBOperator.update(sql, name, content, time);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("guestbook.jsp");
	}
}
