package com.check.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.check.WebConfig;



public class DBManager {
	
	
	public static String CLASSNAME="com.mysql.jdbc.Driver";
	public static String DEFAULTDATABASE=WebConfig.DBNAME;
	public static String URL="jdbc:mysql://127.0.0.1:3306/";
	
	public static String USERNAME="root";
	public static String PASSWORD="fw8839*!)^";
	
	

	
	public static Connection getConnection()throws Exception{
		Class.forName(CLASSNAME);
		return DriverManager.getConnection(URL+DEFAULTDATABASE,USERNAME,PASSWORD);
	}
	
	public static Connection getConnection(String databaseName)throws Exception{
		Class.forName(CLASSNAME);
		return DriverManager.getConnection(URL+databaseName,USERNAME,PASSWORD);
	}
	
	public static void closeConnection(Connection con)throws Exception{
		con.close();
	}
	public static void main(String[] args) throws Exception{
		Connection con=DBManager.getConnection();
		System.out.println(con.getCatalog());
		DBManager.closeConnection(con);
	}
}
