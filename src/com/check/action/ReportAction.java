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

	private List<String> ruleErrorList = new ArrayList<>();

	private Map<String, Object> checkResult = new HashMap();

	public String init(ServerInterface serverInterface) throws Exception {
		Connection conn1 = DBManager.getConnection();
		Statement stmt1 = conn1.createStatement();
		stmt1.execute("TRUNCATE TABLE allvar;");
		stmt1.execute("TRUNCATE TABLE flagvar;");
		DBManager.closeConnection(conn1);
		
//		
//		print("表结构表中flag标识与变量字典中不一致", VarKey.CASE_EQUAL_IS_FLAG, new Dict(), "SELECT * FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2) AND (flag IS NULL OR flag =\"\" ) ORDER BY dictId;");
//
//		
//		print("表结构表中非flag标识与变量字典中不一致", VarKey.CASE_EQUAL_NOT_FLAG, new Dict(), "SELECT * FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType != 2) AND flag IS NOT NULL AND flag !=\"\" ORDER BY dictId;");
//
//		
//		print("变量名与表名一致", VarKey.CASE_TABLE_VAR_NAME, new Dict(), "SELECT * FROM dict WHERE bianliang IN (SELECT tableName FROM structure) ORDER BY dictId;");
//		
//		
//		print("变量名以表名开头", VarKey.CASE_TABLE_START_VAR, new Dict(), "SELECT * FROM dict WHERE bianliang LIKE CONCAT(crf,'%') ORDER BY dictId;");
//
//		
//		print("表名含有关键字", VarKey.CASE_TABLE_HAS_KEY, new Structrue(), "SELECT * FROM structure WHERE tableName IN (SELECT word FROM keyword) ORDER BY Id");
//
//		
//		print("变量名含有关键字", VarKey.CASE_VAR_HAS_KEY, new Dict(), "SELECT * FROM dict WHERE bianliang IN (SELECT word FROM keyword) ORDER BY dictId");
//
//		
//		print("多表变量长度大于8", VarKey.CASE_VAR_LENGTH_8 , new Dict(), "SELECT * FROM dict WHERE LENGTH(bianliang)>8 AND flag IS NOT NULL AND flag!=\"\" ORDER BY dictId;");
//
//		
//		print("单表变量长度大于10", VarKey.CASE_VAR_LENGTH_10, new Dict(), "SELECT * FROM dict WHERE LENGTH(bianliang)>10 AND (flag IS NULL OR flag=\"\") ORDER BY dictId;");
//
//		
//		Connection conn2 = DBManager.getConnection();
//		Statement stmt2 = conn2.createStatement();
//		stmt2.executeUpdate("INSERT INTO allvar (bianliang) SELECT DISTINCT bianliang FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2);");
//		stmt2.executeUpdate("INSERT INTO allvar (bianliang) SELECT bianliang FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType != 2);");
//		DBManager.closeConnection(conn2);
//		print("flag表与非flag表的重变量", VarKey.CASE_ALL_VAR_REPEAT, new Dict(), "select * from dict where bianliang IN (select bianliang from allvar where bianliang in(select bianliang from allvar group by bianliang having count(bianliang)>1) order by bianliang);");
//		
//		
//		Connection conn3 = DBManager.getConnection();
//		Statement stmt3 = conn3.createStatement();
//		stmt3.executeUpdate("INSERT INTO flagvar (bianliang) SELECT CONCAT(bianliang,flag) FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2);");
//		DBManager.closeConnection(conn3);
//		print("所有Flag表的重变量",VarKey.CASE_FLAG_VAR_REPEAT, new Dict(), "select * from flagvar where bianliang in(select bianliang from flagvar where bianliang in(select bianliang from flagvar group by bianliang having count(bianliang)>1) order by bianliang);");
//
//		
//		print("flag表的变量长度不一致", VarKey.CASE_SAME_VAR_LENGTH, new Dict(), "SELECT d1.* FROM dict AS d1,dict AS d2 WHERE (d1.dictId != d2.dictId AND d1.len != d2.len AND d1.bianliang = d2.bianliang AND d1.flag != d2.flag AND d1.flag != \"\" AND d1.flag IS NOT NULL) ORDER BY bianliang,dictId;");
//		
//		
//		Connection conn4 = DBManager.getConnection();
//		Statement stmt4 = conn4.createStatement();
//		ResultSet rs4 = stmt4.executeQuery("SELECT dictId,bvalue FROM dict WHERE bvalue IS NOT NULL AND bvalue != \"\";");
//		DBManager.closeConnection(conn4);
//		checkRule(rs4);
//		int ruleSize = ruleErrorList.size();
//		String ids = "";
//		if (ruleSize > 0) {
//			for (int i = 0; i < ruleSize; i++) {
//				ids += ruleErrorList.get(i);
//				if (i < ruleSize - 1) {
//					ids += ",";
//				}
//			}
//		}
//		if (!"".equals(ids) && ids != null) {
//			print("bvalue编写不规范", VarKey.CASE_B_VAR_RULE, new Dict(), "SELECT * FROM dict WHERE dictId IN ("+ ids + ") ORDER BY dictId;");
//		}
//		
//
//		print("变量名命名有特殊符号", VarKey.CASE_VAR_SPEC_CODE, new Dict(), "SELECT dictId,bianliang FROM dict WHERE bianliang NOT REGEXP \"^[a-zA-Z0-9_]*$\" ORDER BY dictId;");
//	
//		
//		print("flag标记有特殊符号", VarKey.CASE_Flag_SPEC_CODE, new Dict(), "SELECT DISTINCT flag FROM dict WHERE flag NOT REGEXP \"^[a-zA-Z0-9_]*$\" ORDER BY flag;");
//		
		serverInterface.setSessionAttribute("data", checkResult);
		
		return "/WEB-INF/report.jsp";
	}

	private <T> void print(String errorMsg, String tag, T t, String sql)
			throws Exception {
		CRUD crud = new CRUD();
		List<T> results = crud.getBeanFactory().queryBeans(sql, t);
		boolean isFirst = true;
		if (results.size() > 0) {
			if (isFirst) {
				checkResult.put("errorMsg", count + "." + errorMsg);
				count++;
				isFirst = false;
			}
			checkResult.put(tag, results);
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
}
