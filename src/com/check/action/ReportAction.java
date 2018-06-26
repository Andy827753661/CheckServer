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
import com.check.WebConfig;
import com.check.constant.VarKey;
import com.check.db.DBManager;
import com.check.pojo.Dict;
import com.check.pojo.Structrue;
import com.check.sql.CRUD;
import com.google.common.collect.Lists;

import ejw.RequestHandler;
import ejw.ServerInterface;

public class ReportAction extends RequestHandler {

	private int dECount = 1;
	
	private int sECount = 1;

	private Map<Integer,Integer> bdIdMap = new HashMap<>();
	
	private List<String> ruleErrorList = new ArrayList<>();

	private Map<String, Object> checkResult = new HashMap();
	
	private Map<String, Integer> questionCount = new HashMap();

	private List<String> dTagList = new ArrayList<>();
	
	private List<String> sTagList = new ArrayList<>();

	public String init(ServerInterface serverInterface) throws Exception {
		Connection conn = DBManager.getConnection();
		Statement initStmt = conn.createStatement();
		initStmt.execute("TRUNCATE TABLE allvar;");
		initStmt.execute("TRUNCATE TABLE flagvar;");
		initStmt.execute("DELETE FROM dict WHERE crf IN ('subject','site');");
		initStmt.execute("DELETE FROM structure WHERE tableName IN ('subject','site');");
		
		
		print(conn, "表结构表中flag标识与变量字典中不一致", VarKey.CASE_EQUAL_IS_FLAG, new Dict(), "SELECT * FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2) AND (flag IS NULL OR flag ='' ) ORDER BY dictId;");

		
 		print(conn, "表结构表中非flag标识与变量字典中不一致", VarKey.CASE_EQUAL_NOT_FLAG, new Dict(), "SELECT * FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType != 2) AND flag IS NOT NULL AND flag !='' ORDER BY dictId;");

		
		print(conn, "变量名与表名一致", VarKey.CASE_TABLE_VAR_NAME, new Dict(), "SELECT * FROM dict WHERE bianliang=crf ORDER BY dictId;");
		
		
		if(WebConfig.ISPDC){
			print(conn, "PDC课题变量名以表名开头", VarKey.CASE_TABLE_START_VAR, new Dict(), "SELECT * FROM dict WHERE bianliang LIKE CONCAT(crf,'%') AND bianliang NOT LIKE CONCAT(crf,'_num') ORDER BY dictId;");
		}

		
		print(conn, "变量名含有关键字", VarKey.CASE_VAR_HAS_KEY, new Dict(), "SELECT * FROM dict WHERE bianliang IN (SELECT word FROM keyword) ORDER BY dictId");

		
		print(conn, "多表变量长度大于8", VarKey.CASE_VAR_LENGTH_8 , new Dict(), "SELECT * FROM dict WHERE LENGTH(bianliang)>8 AND bianliang NOT LIKE CONCAT('bz_',crf) AND flag IS NOT NULL AND flag!='' ORDER BY dictId;");

		
		print(conn, "单表变量长度大于10", VarKey.CASE_VAR_LENGTH_10, new Dict(), "SELECT * FROM dict WHERE LENGTH(bianliang)>10 AND bianliang NOT LIKE CONCAT('bz_',crf) AND (flag IS NULL OR flag='') ORDER BY dictId;");

		
		initStmt.executeUpdate("INSERT INTO flagvar (bianliang,originId) SELECT CONCAT(bianliang,flag),dictId FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType = 2);");
		ResultSet originIds = initStmt.executeQuery("SELECT f1.originId FROM flagvar f1,flagvar f2 WHERE f1.dictId != f2.dictId AND f1.bianliang = f2.bianliang;");
		ResultSetMetaData originRsmd = originIds.getMetaData();
		ArrayList<String> originIdList = Lists.newArrayList();
		while (originIds.next()) {
			if (originIds.getObject(originRsmd.getColumnName(1)) != null) {
				originIdList.add(originIds.getObject(originRsmd.getColumnName(1)).toString().trim());
			}
		}
		if(originIdList.size() > 0){
			String originIdStr = "";
			for(int i=0; i <originIdList.size(); i++){
				originIdStr += originIdList.get(i);
				if(i != originIdList.size()-1){
					originIdStr += ",";
				}
			}
			
			print(conn, "所有Flag表的重变量",VarKey.CASE_FLAG_VAR_REPEAT, new Dict(), "SELECT * FROM dict WHERE dictId IN("+originIdStr+") ORDER BY bianliang,dictId;");
		}
		
		ResultSet flagTables = initStmt.executeQuery("SELECT tableName FROM structure WHERE tableType = 2");
		ResultSetMetaData flagTablesRsmd = flagTables.getMetaData();
		ArrayList<String> tablesList = Lists.newArrayList();
		while (flagTables.next()) {
			if (flagTables.getObject(flagTablesRsmd.getColumnName(1)) != null) {
				tablesList.add(flagTables.getObject(flagTablesRsmd.getColumnName(1)).toString().trim());
			}
		}
		if(tablesList.size() > 0){
			for(int i = 0; i<tablesList.size(); i++){
				initStmt.executeUpdate("INSERT INTO allvar (bianliang) SELECT DISTINCT bianliang FROM dict WHERE crf ='"+ tablesList.get(i) +"';");
			}
		}
		initStmt.executeUpdate("INSERT INTO allvar (bianliang) SELECT bianliang FROM dict WHERE crf IN (SELECT tableName FROM structure WHERE tableType != 2);");
		ResultSet notFlagTables = initStmt.executeQuery("select a1.bianliang from allvar a1,allvar a2 where a1.dictId != a2.dictId AND a1.bianliang = a2.bianliang");
		ResultSetMetaData notFlagTablesRsmd = notFlagTables.getMetaData();
		ArrayList<String> notFlagTableList = Lists.newArrayList();
		while (notFlagTables.next()) {
			if (notFlagTables.getObject(notFlagTablesRsmd.getColumnName(1)) != null) {
				notFlagTableList.add(notFlagTables.getObject(notFlagTablesRsmd.getColumnName(1)).toString().trim());
			}
		}
		if(notFlagTableList.size() > 0){
			String notFlagTableStr = "";
			for(int i=0; i<notFlagTableList.size(); i++){
				notFlagTableStr += "'"+notFlagTableList.get(i)+"'";
				if(i != notFlagTableList.size()-1){
					notFlagTableStr += ",";
				}
			}
			print(conn, "flag表与非flag表的重变量", VarKey.CASE_ALL_VAR_REPEAT, new Dict(), "select * from dict where bianliang IN ("+notFlagTableStr+") ORDER BY bianliang,dictId;");
		}
		
		
		ArrayList<String> flagLengthIdList = Lists.newArrayList();
		if(tablesList.size() > 0){
			for(int i=0; i<tablesList.size(); i++){
				ResultSet flagLengthIds = initStmt.executeQuery("SELECT DISTINCT d1.dictId FROM (SELECT * FROM dict WHERE flag is not NULL AND flag !='') AS d1,(SELECT * FROM dict WHERE flag is not NULL AND flag !='') AS d2 WHERE (d1.bianliang = d2.bianliang AND d1.crf = d2.crf AND d1.len != d2.len);");
				ResultSetMetaData flagLengthRsmd = flagLengthIds.getMetaData();
				while (flagLengthIds.next()) {
					if (flagLengthIds.getObject(flagLengthRsmd.getColumnName(1)) != null) {
						flagLengthIdList.add(flagLengthIds.getObject(flagLengthRsmd.getColumnName(1)).toString().trim());
					}
				}
			}
		}
		if(flagLengthIdList.size() > 0){
			String flagLengthIdStr = "";
			for(int i=0; i<flagLengthIdList.size(); i++){
				flagLengthIdStr += flagLengthIdList.get(i);
				if(i != flagLengthIdList.size() - 1){
					flagLengthIdStr += ",";
				}
			}
			print(conn, "flag表的变量长度不一致", VarKey.CASE_SAME_VAR_LENGTH, new Dict(), "SELECT * FROM DICT WHERE dictId IN("+flagLengthIdStr+") ORDER BY bianliang,dictId;");
		}
		
		
		ResultSet rs4 = initStmt.executeQuery("SELECT dictId,bvalue FROM dict WHERE bvalue IS NOT NULL AND bvalue != '';");
		checkRule(rs4);
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
		if (!"".equals(bValueIds) && bValueIds != null) {
			print(conn, "bvalue编写不规范", VarKey.CASE_B_VAR_RULE, new Dict(), "SELECT * FROM dict WHERE dictId IN ("+ bValueIds + ") ORDER BY dictId;");
		}
		

		print(conn, "变量名命名有特殊符号", VarKey.CASE_VAR_SPEC_CODE, new Dict(), "SELECT * FROM dict WHERE bianliang NOT REGEXP '^[a-zA-Z0-9_]*$' ORDER BY dictId;");
	
		
		print(conn, "flag标记有特殊符号", VarKey.CASE_Flag_SPEC_CODE, new Dict(), "SELECT * FROM dict WHERE flag NOT REGEXP '^[a-zA-Z0-9_]*$' ORDER BY flag;");		
		
		

		ResultSet rs5 = initStmt.executeQuery("SELECT * FROM dict;");
		checkBD(rs5);
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
		if (!"".equals(bdIds) && bdIds != null) {			
			print(conn, "变量字典中有英文的逗号和英文的双引号", VarKey.CASE_DICT_HAS_BD, new Dict(), "SELECT * FROM dict WHERE dictId IN ("+bdIds+");");		
		}
		
		
		if(WebConfig.checkFlag){
			ResultSet flagIds = initStmt.executeQuery("select min(dictId) from dict where flag not like 'v%' AND flag != '' AND flag IS NOT NULL group by flag");
			ResultSetMetaData flagIdRsmd = flagIds.getMetaData();
			ArrayList<String> flagIdList = Lists.newArrayList();
			while (flagIds.next()) {
				if (flagIds.getObject(flagIdRsmd.getColumnName(1)) != null) {
					flagIdList.add(flagIds.getObject(flagIdRsmd.getColumnName(1)).toString().trim());
				}
			}
			if(flagIdList.size() > 0){
				String flagIdStr = "";
				for(int j=0;j<flagIdList.size();j++){
					flagIdStr += flagIdList.get(j);
					if(j != flagIdList.size() - 1){
						flagIdStr += ",";
					}
				}
				print(conn, "变量字典中访视标记不能形同V1，V2...", VarKey.CASE_FLAG_V1_V2,new Dict(), "select * from dict where dictId IN ("+flagIdStr+");");
			}
		}

		
		print(conn, "变量字典比表结构表多余的表",VarKey.CASE_DICT_MORE_STRUCTURE,new Dict(),"SELECT * FROM dict WHERE crf NOT IN(SELECT tableName FROM structure)GROUP BY crf ORDER BY dictId;");
		
		/**********Structrue**********/
		print(conn, "表名含有关键字", VarKey.CASE_TABLE_HAS_KEY, new Structrue(), "SELECT * FROM structure WHERE tableName IN (SELECT word FROM keyword) ORDER BY Id");

		
		print(conn, "表结构表中的“表类型”与“标识”不一致", VarKey.CASE_TYPE_FLAG, new Structrue(), "SELECT * FROM structure WHERE (tableType = 1 AND (tablelx != '单表' OR (tableItem IS NOT NULL AND tableItem !=''))) OR (tableType = 2 AND(tablelx !='多表'   OR tableItem != 'flag')) OR (tableType = 3 AND(tablelx !='多表' OR tableItem NOT LIKE '%_num'));");

		
		print(conn, "表结构表中的“关键字”不符合“表名_id”的形式", VarKey.CASE_STRUCTURE_KEY, new Structrue(), "SELECT * FROM structure WHERE tableKey NOT LIKE CONCAT(tableName,'_id');");
		
		
		print(conn, "表结构表中的“标识变量”不符合规范（flag、表名_num）", VarKey.CASE_STRUCTURE_ITEM, new Structrue(), "SELECT * FROM structure WHERE (tableType = 3 AND tableItem NOT LIKE CONCAT(tableName,'_num')) OR (tableType = 2 AND tableItem != 'flag');");
		
		
		print(conn, "表结构表的表名不是字母和数字的组合", VarKey.CASE_STRUCTURE_TABLE_NAME, new Structrue(), "SELECT * FROM structure WHERE tableName NOT REGEXP '^[a-zA-Z0-9]*$';");
		
		
		print(conn, "表结构表比变量字典多余的表",VarKey.CASE_STRUCTURE_MORE_DICT,new Structrue(),"SELECT * FROM structure WHERE tableName NOT IN(SELECT DISTINCT crf FROM dict) ORDER BY Id;");
		
		DBManager.closeConnection(conn);
		
		serverInterface.setAttribute("data", checkResult);
		serverInterface.setAttribute("dTag", dTagList);
		serverInterface.setAttribute("sTag", sTagList);
		serverInterface.setAttribute("bdTag", bdIdMap);
		serverInterface.setAttribute("count", questionCount);
		
		return "/WEB-INF/report.jsp";
	}

	private <T> void print(Connection conn,String errorMsg, String tag, T t, String sql)
			throws Exception {
		CRUD crud = new CRUD();
		List<T> results = crud.getBeanFactory().queryBeans(conn, sql, t);
		System.out.println("errorMsg == > " + errorMsg);
		if (results.size() > 0) {
			checkResult.put(tag, results);
			if(t instanceof Dict){				
				checkResult.put(tag+"_msg", dECount + "." + errorMsg);
				questionCount.put(tag+"_msg", results.size());
				dECount++;
				dTagList .add(tag);
			}else if(t instanceof Structrue){
				checkResult.put(tag+"_msg", sECount + "." + errorMsg);
				questionCount.put(tag+"_msg", results.size());
				sECount++;
				sTagList.add(tag);
			}
		}
	}
	
	
	@SuppressWarnings("unused")
	private <T> void print(Connection conn, String errorMsg, String tag, T t, String[] sqlList)
			throws Exception {
		CRUD crud = new CRUD();
		List<T> results = new ArrayList<>();
		for(String sql : sqlList){
			List<T> result = crud.getBeanFactory().queryBeans(conn, sql, t);
			results.addAll(result);
		}
		
		if (results.size() > 0) {
			checkResult.put(tag, results);
			if(t instanceof Dict){				
				checkResult.put(tag+"_msg", dECount + "." + errorMsg);
				questionCount.put(tag+"_msg", results.size());
				dECount++;
				dTagList .add(tag);
			}else if(t instanceof Structrue){
				checkResult.put(tag+"_msg", sECount + "." + errorMsg);
				questionCount.put(tag+"_msg", results.size());
				sECount++;
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
					outString = rs.getObject(rsmd.getColumnName(2)).toString().trim();
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
