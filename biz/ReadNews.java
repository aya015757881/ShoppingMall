package com.hwua.biz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hwua.bean.News;

public class ReadNews extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String newsIdStr = req.getParameter("newsId");
		int newsId = Integer.parseInt(newsIdStr);
		
		ArrayList<News> newsList = (ArrayList<News>) req.getAttribute("news");
		
		News news = new News();
		
		for(News n : newsList)
			if(n.getId()==newsId){
				news = n;
				break;
			}
		
		req.setAttribute("newsInfo", news);
		req.getRequestDispatcher("news_view.jsp").forward(req, resp);
	}
}
