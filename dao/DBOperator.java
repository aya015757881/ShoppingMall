package com.hwua.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import com.hwua.util.DBHelper;

public class DBOperator
{
	public static ResultSet getResult(String sql,Object... obj) throws SQLException
	{
		DBHelper.getSQL(sql);
		for(int i=0;i<obj.length;i++)
			DBHelper.getPstm().setObject(i+1,obj[i]);
		return DBHelper.getPstm().executeQuery();
	}
	
	public static boolean hasResult(String sql,Object... obj) throws SQLException
	{
		DBHelper.getSQL(sql);
		for(int i=0;i<obj.length;i++)
			DBHelper.getPstm().setObject(i+1,obj[i]);
		if(DBHelper.getPstm().executeQuery().next())
			return true;
		return false;
	}
	
	public static int update(String sql,Object... obj) throws SQLException
	{
		DBHelper.getSQL(sql);
		for(int i=0;i<obj.length;i++)
			DBHelper.getPstm().setObject(i+1,obj[i]);
		return DBHelper.getPstm().executeUpdate();
	}
}
