package com.check.sql;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.check.db.DBManager;
import com.google.common.collect.Maps;

public class BeanFactory{
	//结果变成Bean对象
	private <T> T resultSetToBean(ResultSet rs,Class<T> clazz)throws Exception{
		T t=(T)clazz.newInstance();
		String tableName=t.getClass().getSimpleName();
		Field[] fields=clazz.getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			String fieldName=field.getName();
			field.set(t, Converter.convertObject(field.getType(), rs.getObject(fieldName)));
		}
		return t;
	}
	//参数是Bean
	public <T> T queryBean(String sql,T t)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		T result=null;
		if(rs.next()){
			result=(T)resultSetToBean(rs, t.getClass());
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Bean
	public <T> T queryBean(Connection con,String sql,T t)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		T result=null;
		if(rs.next()){
			result=(T)resultSetToBean(rs, t.getClass());
		}
		return result;
	}
	//参数是Class
	public <T> T queryBean(String sql,Class<T> clazz)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		T result=null;
		if(rs.next()){
			result=(T)resultSetToBean(rs, clazz);
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Class
	public <T> T queryBean(Connection con,String sql,Class<T> clazz)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		T result=null;
		if(rs.next()){
			result=(T)resultSetToBean(rs,clazz);
		}
		return result;
	}
	//参数是Map
	public <T> T queryBean(String sql,Map<String, Object> map,Class<T> clazz)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		T result=null;
		if(rs.next()){
			result=(T)resultSetToBean(rs,clazz);
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Map
	public <T> T queryBean(Connection con,String sql,Map<String, Object> map,Class<T> clazz)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		T result=null;
		if(rs.next()){
			result=(T)resultSetToBean(rs,clazz);
		}
		return result;
	}
	
	
	//参数是Bean
	public <T> List<T> queryBeans(String sql,T t)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		List<T> result=new ArrayList<T>();
		Class clazz=t.getClass();
		while(rs.next()){
			result.add((T)resultSetToBean(rs, clazz));
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Bean
	public <T> List<T> queryBeans(Connection con,String sql,T t)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(t))).executeQuery();
		List<T> result=new ArrayList<T>();
		Class clazz=t.getClass();
		while(rs.next()){
			result.add((T)resultSetToBean(rs, clazz));
		}
		return result;
	}
	//参数是Class
	public <T> List<T> queryBeans(String sql,Class<T> clazz)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		List<T> result=new ArrayList<T>();
		while(rs.next()){
			result.add((T)resultSetToBean(rs, clazz));
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Class
	public <T> List<T> queryBeans(Connection con,String sql,Class<T> clazz)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		List<T> result=new ArrayList<T>();
		while(rs.next()){
			result.add((T)resultSetToBean(rs, clazz));
		}
		return result;
	}
	//参数是Map
	public <T> List<T> queryBeans(String sql,Map<String, Object> map,Class<T> clazz)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		List<T> result=new ArrayList<T>();
		while(rs.next()){
			result.add((T)resultSetToBean(rs, clazz));
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Map
	public <T> List<T> queryBeans(Connection con,String sql,Map<String, Object> map,Class<T> clazz)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		List<T> result=new ArrayList<T>();
		while(rs.next()){
			result.add((T)resultSetToBean(rs, clazz));
		}
		return result;
	}
	
	
	//参数是Bean
	public <T> List<Map<Class,T>> queryMultiBeans(String sql,T...ts)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(ts))).executeQuery();
		List<Map<Class,T>> result=new ArrayList<Map<Class,T>>();
		Map<Class,T> map=Maps.newHashMap();
		while(rs.next()){
			for(T t:ts){
				Class clazz=t.getClass();
				map.put(clazz, (T)resultSetToBean(rs, clazz));
			}
			result.add(map);
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Bean
	public <T> List<Map<Class,T>> queryMultiBeans(Connection con,String sql,T...ts)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, Converter.beanToMap(ts))).executeQuery();
		List<Map<Class,T>> result=new ArrayList<Map<Class,T>>();
		Map<Class,T> map=Maps.newHashMap();
		while(rs.next()){
			for(T t:ts){
				Class clazz=t.getClass();
				map.put(clazz, (T)resultSetToBean(rs, clazz));
			}
			result.add(map);
		}
		return result;
	}
	//参数是Class
	public <T> List<Map<Class,T>> queryMultiBeans(String sql,Class<T>...classes)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		List<Map<Class,T>> result=new ArrayList<Map<Class,T>>();
		Map<Class,T> map=Maps.newHashMap();
		while(rs.next()){
			for(Class clazz:classes){
				map.put(clazz, (T)resultSetToBean(rs, clazz));
			}
			result.add(map);
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Class
	public <T> List<Map<Class,T>> queryMultiBeans(Connection con,String sql,Class<T>...classes)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, null)).executeQuery();
		List<Map<Class,T>> result=new ArrayList<Map<Class,T>>();
		Map<Class,T> map=Maps.newHashMap();
		while(rs.next()){
			for(Class clazz:classes){
				map.put(clazz, (T)resultSetToBean(rs, clazz));
			}
			result.add(map);
		}
		return result;
	}
	//参数是Map
	public <T> List<Map<Class,T>> queryMultiBeans(String sql,Map<String, Object> map,Class<T>...classes)throws Exception{
		Connection con=DBManager.getConnection();
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		List<Map<Class,T>> result=new ArrayList<Map<Class,T>>();
		Map<Class,T> m=Maps.newHashMap();
		while(rs.next()){
			for(Class clazz:classes){
				m.put(clazz, (T)resultSetToBean(rs, clazz));
			}
			result.add(m);
		}
		DBManager.closeConnection(con);
		return result;
	}
	//参数是Map
	public <T> List<Map<Class,T>> queryMultiBeans(Connection con,String sql,Map<String, Object> map,Class<T>...classes)throws Exception{
		ResultSet rs=con.prepareStatement(SQLWrapper.sqlOf(sql, map)).executeQuery();
		List<Map<Class,T>> result=new ArrayList<Map<Class,T>>();
		Map<Class,T> m=Maps.newHashMap();
		while(rs.next()){
			for(Class clazz:classes){
				m.put(clazz, (T)resultSetToBean(rs, clazz));
			}
			result.add(m);
		}
		return result;
	}
}
