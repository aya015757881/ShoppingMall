package com.hwua.biz;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.hwua.bean.Comment;
import com.hwua.dao.DBOperator;

public class CommentsGetter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		ArrayList<Comment> comments = new ArrayList<Comment>();
		
		String sql = "select * from hwua_comment";
		
		try {
			ResultSet rs = DBOperator.getResult(sql);
			while(rs.next()){
				Comment c = new Comment();
				c.setNickName(rs.getString("hc_nick_name"));
				c.setContent(rs.getString("hc_content"));
				c.setCreateTime(rs.getString("hc_create_time"));
				c.setReply(rs.getString("hc_reply"));
				c.setReplyTime(rs.getString("hc_reply_time"));
				comments.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("comments", comments);
		chain.doFilter(request, response);
	}
}
