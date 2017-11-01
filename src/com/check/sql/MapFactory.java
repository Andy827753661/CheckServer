package com.check.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.check.db.DBManager;
import com.google.common.collect.Maps;

public class MapFactory{
	//结果变成Map对象
	private Map<String,Object> resultSetToMap(ResultSet rs)throws Exception{
		Map<String,Object> map=Maps.newHashMap();
		ResultSetMetaData rsmd=rs.getMetaData();
		for(int i=1,len=rsmd.getColumnCount();i<=len;i++){
			map.put(rsmd.getColumnName(i), rs.getObject(i));
		}
		return map;
	}
	
	//参数是Bean
	public <T> Map<String, Object> queryMap(String sql,T t)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		Map<String, Object> result=null;
		if(rs.next()){
			result=resultSetToMap(rs);
		}
		con.close();
		return result;
	}
	//参数是Bean
	public <T> Map<String, Object> queryMap(Connection con,String sql,T t)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		Map<String, Object> result=null;
		if(rs.next()){
			result=resultSetToMap(rs);
		}
		return result;
	}
	//参数是Map
	public Map<String, Object> queryMap(String sql,Map<String, Object> map)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		Map<String, Object> result=null;
		if(rs.next()){
			result=resultSetToMap(rs);
		}
		con.close();
		return result;
	}
	//参数是Map
	public Map<String, Object> queryMap(Connection con,String sql,Map<String, Object> map)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		Map<String, Object> result=null;
		if(rs.next()){
			result=resultSetToMap(rs);
		}
		return result;
	}
	//无参数
	public Map<String, Object> queryMap(String sql)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		Map<String, Object> result=null;
		if(rs.next()){
			result=resultSetToMap(rs);
		}
		con.close();
		return result;
	}
	//无参数
	public Map<String, Object> queryMap(Connection con,String sql)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		Map<String, Object> result=null;
		if(rs.next()){
			result=resultSetToMap(rs);
		}
		return result;
	}
	//参数是Bean
	public <T> List<Map<String, Object>> queryMaps(String sql,T t)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		while(rs.next()){
			result.add(resultSetToMap(rs));
		}
		con.close();
		return result;
	}
	//参数是Bean
	public <T> List<Map<String, Object>> queryMaps(Connection con,String sql,T t)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		while(rs.next()){
			result.add(resultSetToMap(rs));
		}
		return result;
	}
	//参数是Map
	public List<Map<String, Object>> queryMaps(String sql,Map<String, Object> map)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		while(rs.next()){
			result.add(resultSetToMap(rs));
		}
		con.close();
		return result;
	}
	//参数是Map
	public List<Map<String, Object>> queryMaps(Connection con,String sql,Map<String, Object> map)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		while(rs.next()){
			result.add(resultSetToMap(rs));
		}
		return result;
	}
	//无参数
	public List<Map<String, Object>> queryMaps(String sql)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		while(rs.next()){
			result.add(resultSetToMap(rs));
		}
		con.close();
		return result;
	}
	//无参数
	public List<Map<String, Object>> queryMaps(Connection con,String sql)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		while(rs.next()){
			result.add(resultSetToMap(rs));
		}
		return result;
	}
}
