package com.hwua.biz;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.hwua.util.DBHelper;

public class DBStarter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			DBHelper.getConnected();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		chain.doFilter(request, response);
	}
}
