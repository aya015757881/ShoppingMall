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

import com.hwua.bean.News;
import com.hwua.dao.DBOperator;
import com.hwua.util.DBHelper;

public class NewsView implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request.getAttribute("news")==null)
			request.setAttribute("news", getNews());
		chain.doFilter(request, response);
	}
	
	private ArrayList<News> getNews(){
		
		try {
			DBHelper.getConnected();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "select * from hwua_news";
		ArrayList<News> newsList = new ArrayList<News>();
		
		try {
			ResultSet rs = DBOperator.getResult(sql);
			while(rs.next()){
				News news = new News();
				news.setId(rs.getInt("hn_id"));
				news.setTitle(rs.getString("hn_title"));
				news.setContent(rs.getString("hn_content"));
				news.setCreateTime(rs.getString("hn_create_time"));
				newsList.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newsList;
	}
}
