package com.check.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.check.constant.VarKey;
import com.check.db.DBManager;
import com.check.pojo.Dict;
import com.check.pojo.Structrue;
import com.check.sql.CRUD;

import ejw.RequestHandler;
import ejw.ServerInterface;

public class ReportAction extends RequestHandler {

	private int count = 1;

	private Map<Integer,Integer> bdIdMap = new HashMap<>();
	
	private List<String> ruleErrorList = new ArrayList<>();

	private Map<String, Object> checkResult = new HashMap();

	private List<String> dTagList = new ArrayList<>();
	
	private List<String> sTagList = new ArrayList<>();

	public String init(ServerInterface serverInterface) throws Exception {
		Connection conn1 = DBManager.getConnection();
		Statement stmt1 = conn1.createStatement();
		stmt1.execute("TRUNCATE TABLE allvar;");
		stmt1.execute("TRUNCATE TABLE flagvar;");
		DBManager.closeConnection(conn1);
		
		
		print("表结构表中flag标识与变量字典中不一致", VarKey.CASE_EQUAL_IS_FLAG, new Dict(), "SELECT * FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2) AND (flag IS NULL OR flag =\"\" ) ORDER BY dictId;");

		
 		print("表结构表中非flag标识与变量字典中不一致", VarKey.CASE_EQUAL_NOT_FLAG, new Dict(), "SELECT * FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType != 2) AND flag IS NOT NULL AND flag !=\"\" ORDER BY dictId;");

		
		print("变量名与表名一致", VarKey.CASE_TABLE_VAR_NAME, new Dict(), "SELECT * FROM dict WHERE bianliang IN (SELECT tableName FROM structure) ORDER BY dictId;");
		
		
		print("变量名以表名开头", VarKey.CASE_TABLE_START_VAR, new Dict(), "SELECT * FROM dict WHERE bianliang LIKE CONCAT(crf,'%') ORDER BY dictId;");

		
		print("表名含有关键字", VarKey.CASE_TABLE_HAS_KEY, new Structrue(), "SELECT * FROM structure WHERE tableName IN (SELECT word FROM keyword) ORDER BY Id");

		
		print("变量名含有关键字", VarKey.CASE_VAR_HAS_KEY, new Dict(), "SELECT * FROM dict WHERE bianliang IN (SELECT word FROM keyword) ORDER BY dictId");

		
		print("多表变量长度大于8", VarKey.CASE_VAR_LENGTH_8 , new Dict(), "SELECT * FROM dict WHERE LENGTH(bianliang)>8 AND flag IS NOT NULL AND flag!=\"\" ORDER BY dictId;");

		
		print("单表变量长度大于10", VarKey.CASE_VAR_LENGTH_10, new Dict(), "SELECT * FROM dict WHERE LENGTH(bianliang)>10 AND (flag IS NULL OR flag=\"\") ORDER BY dictId;");

		
		Connection conn2 = DBManager.getConnection();
		Statement stmt2 = conn2.createStatement();
		stmt2.executeUpdate("INSERT INTO allvar (bianliang) SELECT DISTINCT bianliang FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2);");
		stmt2.executeUpdate("INSERT INTO allvar (bianliang) SELECT bianliang FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType != 2);");
		DBManager.closeConnection(conn2);
		print("flag表与非flag表的重变量", VarKey.CASE_ALL_VAR_REPEAT, new Dict(), "select * from dict where bianliang IN (select a1.bianliang from allvar a1,allvar a2 where a1.dictId != a2.dictId AND a1.bianliang = a2.bianliang) order by bianliang;");
		
		
		Connection conn3 = DBManager.getConnection();
		Statement stmt3 = conn3.createStatement();
		stmt3.executeUpdate("INSERT INTO flagvar (bianliang,originId) SELECT CONCAT(bianliang,flag),dictId FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2);");
		DBManager.closeConnection(conn3);
		print("所有Flag表的重变量",VarKey.CASE_FLAG_VAR_REPEAT, new Dict(), "SELECT * FROM dict WHERE dictId IN(SELECT f1.originId FROM flagvar f1,flagvar f2 WHERE f1.dictId != f2.dictId AND f1.bianliang = f2.bianliang) ORDER BY bianliang;");

		
		print("flag表的变量长度不一致", VarKey.CASE_SAME_VAR_LENGTH, new Dict(), "SELECT d1.* FROM dict AS d1,dict AS d2 WHERE (d1.dictId != d2.dictId AND d1.len != d2.len AND d1.bianliang = d2.bianliang AND d1.flag != d2.flag AND d1.flag != \"\" AND d1.flag IS NOT NULL) ORDER BY bianliang;");
		
		
		Connection conn4 = DBManager.getConnection();
		Statement stmt4 = conn4.createStatement();
		ResultSet rs4 = stmt4.executeQuery("SELECT dictId,bvalue FROM dict WHERE bvalue IS NOT NULL AND bvalue != \"\";");
		checkRule(rs4);
		DBManager.closeConnection(conn4);
		int ruleSize = ruleErrorList.size();
		String bValueIds = "";
		if (ruleSize > 0) {
			for (int i = 0; i < ruleSize; i++) {
				bValueIds += ruleErrorList.get(i);
				if (i < ruleSize - 1) {
					bValueIds += ",";
				}
			}
		}
		System.out.println("bValueIds == > " +bValueIds);
		if (!"".equals(bValueIds) && bValueIds != null) {
			print("bvalue编写不规范", VarKey.CASE_B_VAR_RULE, new Dict(), "SELECT * FROM dict WHERE dictId IN ("+ bValueIds + ") ORDER BY dictId;");
		}
		

		print("变量名命名有特殊符号", VarKey.CASE_VAR_SPEC_CODE, new Dict(), "SELECT * FROM dict WHERE bianliang NOT REGEXP \"^[a-zA-Z0-9_]*$\" ORDER BY dictId;");
	
		
		print("flag标记有特殊符号", VarKey.CASE_Flag_SPEC_CODE, new Dict(), "SELECT * FROM dict WHERE flag NOT REGEXP \"^[a-zA-Z0-9_]*$\" ORDER BY flag;");		
		
		

		Connection conn5 = DBManager.getConnection();
		Statement stmt5 = conn5.createStatement();
		ResultSet rs5 = stmt5.executeQuery("SELECT * FROM dict;");
		checkBD(rs5);
		DBManager.closeConnection(conn5);
		int bdMapSize = bdIdMap.size();
		String bdIds = "";
		int i = 0;
		if (bdMapSize > 0) {
			for(int id : bdIdMap.keySet()) {
				bdIds += id;
				if (i < bdMapSize - 1) {
					bdIds += ",";
				}
				i++;
			}
		}
		System.out.println("bdIds == "+bdIds);
		if (!"".equals(bdIds) && bdIds != null) {			
			print("变量字典中有英文的逗号和英文的双引号", VarKey.CASE_DICT_HAS_BD, new Dict(), "SELECT * FROM dict WHERE dictId IN ("+bdIds+");");		
		}
		
		print("变量字典中访视标记不能形同V1，V2...", VarKey.CASE_FLAG_V1_V2,new Dict(), "SELECT * FROM dict WHERE flag not like \"v%\" AND flag != \"\" AND flag IS NOT NULL;");

		
		serverInterface.setAttribute("data", checkResult);
		serverInterface.setAttribute("dTag", dTagList);
		serverInterface.setAttribute("sTag", sTagList);
		serverInterface.setAttribute("bdTag", bdIdMap);
		
		return "/WEB-INF/report.jsp";
	}

	private <T> void print(String errorMsg, String tag, T t, String sql)
			throws Exception {
		CRUD crud = new CRUD();
		List<T> results = crud.getBeanFactory().queryBeans(sql, t);
		boolean isFirst = true;
		if (results.size() > 0) {
			if (isFirst) {
				checkResult.put(tag+"_msg", count + "." + errorMsg);
				count++;
				isFirst = false;
			}
			checkResult.put(tag, results);
			if(t instanceof Dict){				
				dTagList .add(tag);
			}else if(t instanceof Structrue){
				sTagList.add(tag);
			}
		}
	}

	private void checkRule(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				String outString = "";
				if (rs.getObject(rsmd.getColumnName(2)) != null) {
					outString = rs.getObject(rsmd.getColumnName(2)).toString()
							.trim();
				}
				String[] bValues = outString.split(";");
				eachValue: for (String bValue : bValues) {
					int dhCount = 0;
					int len = bValue.length();
					for (int i = 0; i < len; i++) {
						if (bValue.charAt(i) == '=') {
							dhCount++;
						}
					}
					if (dhCount != 1) {
						String outStrId = rs.getObject(rsmd.getColumnName(1))
								.toString().trim();
						ruleErrorList.add(outStrId);
						break eachValue;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkBD(ResultSet rs){
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				for(int j=1;j<=rsmd.getColumnCount();j++){
					String outString = "";
					if(rs.getObject(rsmd.getColumnName(j)) != null){
						outString = rs.getObject(rsmd.getColumnName(j)).toString();
						if(outString.contains(",") || outString.contains("\"")){
							bdIdMap.put(rs.getInt(rsmd.getColumnName(1)), j);
							System.out.println("rsmd.getColumnName(j) == "+rsmd.getColumnName(j) +"    j == "+j);
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
