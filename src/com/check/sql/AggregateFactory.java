package com.check.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import com.check.db.DBManager;


public class AggregateFactory {
	public <T> int queryCount(String sql,T t)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		int count=0;
		if(rs.next()){
			count=rs.getInt(1);
		}
		DBManager.closeConnection(con);
		return count;
	}
	public <T> int queryCount(Connection con,String sql,T t)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		int count=0;
		if(rs.next()){
			count=rs.getInt(1);
		}
		return count;
	}
	public <T> int queryCount(String sql,Map<String, Object> map)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		int count=0;
		if(rs.next()){
			count=rs.getInt(1);
		}
		DBManager.closeConnection(con);
		return count;
	}
	public <T> int queryCount(Connection con,String sql,Map<String, Object> map)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		int count=0;
		if(rs.next()){
			count=rs.getInt(1);
		}
		return count;
	}
	
	
	
}
