package com.check.sql;

import java.sql.Connection;
import java.util.Map;

import com.check.db.DBManager;


public class ModificationFactory {
	public int update(String sql,Map<String, Object> map)throws Exception{
		Connection con=DBManager.getConnection();
		int result=0;
		result=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeUpdate();
		con.close();
		return result;
	}
	public int update(Connection con,String sql,Map<String, Object> map)throws Exception{
		int result=0;
		result=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeUpdate();
		return result;
	}
	public <T> int update(String sql,T t)throws Exception{
		Connection con=DBManager.getConnection();
		int result=0;
		result=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeUpdate();
		con.close();
		return result;
	}
	public <T> int update(Connection con,String sql,T t)throws Exception{
		int result=0;
		result=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeUpdate();
		return result;
	}
	public int update(String sql)throws Exception{
		return update(sql, null);
	}
	public int update(Connection con,String sql)throws Exception{
		return update(con,sql,null);
	}
}
