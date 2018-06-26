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
		System.err.println("核查开始");
		Connection conn = DBManager.getConnection();
		Statement initStmt = conn.createStatement();
		initStmt.execute("TRUNCATE TABLE ALLVAR;");
		initStmt.execute("TRUNCATE TABLE FLAGVAR;");
		initStmt.execute("DELETE FROM DICT WHERE CRF IN ('SUBJECT','SITE');");
		initStmt.execute("DELETE FROM STRUCTURE WHERE TABLENAME IN ('SUBJECT','SITE');");
		
		/*所有Flag表的重变量 START*/
		initStmt.executeUpdate("INSERT INTO FLAGVAR (BIANLIANG,ORIGINID) SELECT CONCAT(BIANLIANG,FLAG),DICTID FROM DICT WHERE CRF IN (SELECT TABLENAME FROM STRUCTURE WHERE TABLETYPE = 2);");
		ResultSet originIds = initStmt.executeQuery("SELECT F1.ORIGINID FROM FLAGVAR F1,FLAGVAR F2 WHERE F1.BIANLIANG = F2.BIANLIANG AND F1.DICTID != F2.DICTID;");
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
			
			print(conn, "所有Flag表的重变量",VarKey.CASE_FLAG_VAR_REPEAT, new Dict(), "SELECT * FROM DICT WHERE DICTID IN("+originIdStr+") ORDER BY BIANLIANG,DICTID;");
		}
		/*所有Flag表的重变量 END*/
		
		
		/*flag表与非flag表的重变量 START*/
		ResultSet flagTables = initStmt.executeQuery("SELECT TABLENAME FROM STRUCTURE WHERE TABLETYPE = 2;");
		ResultSetMetaData flagTablesRsmd = flagTables.getMetaData();
		ArrayList<String> tablesList = Lists.newArrayList();
		while (flagTables.next()) {
			if (flagTables.getObject(flagTablesRsmd.getColumnName(1)) != null) {
				tablesList.add(flagTables.getObject(flagTablesRsmd.getColumnName(1)).toString().trim());
			}
		}
		if(tablesList.size() > 0){
			for(int i = 0; i<tablesList.size(); i++){
				initStmt.executeUpdate("INSERT INTO ALLVAR (BIANLIANG) SELECT DISTINCT BIANLIANG FROM DICT WHERE CRF ='"+ tablesList.get(i) +"';");
			}
		}
		initStmt.executeUpdate("INSERT INTO ALLVAR (BIANLIANG) SELECT BIANLIANG FROM DICT WHERE CRF IN (SELECT TABLENAME FROM STRUCTURE WHERE TABLETYPE != 2);");
		ResultSet notFlagTables = initStmt.executeQuery("SELECT A.BIANLIANG FROM ALLVAR A,ALLVAR B WHERE A.DICTID != B.DICTID AND A.BIANLIANG = B.BIANLIANG");
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
			print(conn, "flag表与非flag表的重变量", VarKey.CASE_ALL_VAR_REPEAT, new Dict(), "SELECT * FROM DICT WHERE BIANLIANG IN ("+notFlagTableStr+") ORDER BY BIANLIANG,DICTID;");
		}
		/*flag表与非flag表的重变量 END*/
		
		
		/*flag表的变量长度不一致 START*/
		ArrayList<String> flagLengthIdList = Lists.newArrayList();
		if(tablesList.size() > 0){
			for(int i=0; i<tablesList.size(); i++){
				ResultSet flagLengthIds = initStmt.executeQuery("SELECT DISTINCT D1.DICTID FROM (SELECT * FROM DICT WHERE FLAG IS NOT NULL AND FLAG !='') AS D1,(SELECT * FROM DICT WHERE FLAG IS NOT NULL AND FLAG !='') AS D2 WHERE (D1.BIANLIANG = D2.BIANLIANG AND D1.CRF = D2.CRF AND D1.LEN != D2.LEN);");
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
			print(conn, "flag表的变量长度不一致", VarKey.CASE_SAME_VAR_LENGTH, new Dict(), "SELECT * FROM DICT WHERE DICTID IN("+flagLengthIdStr+") ORDER BY BIANLIANG,DICTID;");
		}
		/*flag表的变量长度不一致 END*/
		
		
		print(conn, "表结构表中flag标识与变量字典中不一致", VarKey.CASE_EQUAL_IS_FLAG, new Dict(), "SELECT * FROM DICT WHERE CRF IN (SELECT TABLENAME FROM STRUCTURE WHERE TABLETYPE = 2) AND (FLAG IS NULL OR FLAG ='' ) ORDER BY DICTID;");

		
 		print(conn, "表结构表中非flag标识与变量字典中不一致", VarKey.CASE_EQUAL_NOT_FLAG, new Dict(), "SELECT * FROM DICT WHERE CRF IN (SELECT TABLENAME FROM STRUCTURE WHERE TABLETYPE != 2) AND FLAG IS NOT NULL AND FLAG !='' ORDER BY DICTID;");

		
		print(conn, "变量名与表名一致", VarKey.CASE_TABLE_VAR_NAME, new Dict(), "SELECT * FROM DICT WHERE BIANLIANG=CRF ORDER BY DICTID;");
		
		
		if(WebConfig.ISPDC){
			print(conn, "PDC课题变量名以表名开头", VarKey.CASE_TABLE_START_VAR, new Dict(), "SELECT * FROM DICT WHERE BIANLIANG LIKE CONCAT(CRF,'%') AND BIANLIANG NOT LIKE CONCAT(CRF,'_num') ORDER BY DICTID;");
		}

		
		print(conn, "变量名含有关键字", VarKey.CASE_VAR_HAS_KEY, new Dict(), "SELECT * FROM DICT WHERE BIANLIANG IN (SELECT WORD FROM KEYWORD) ORDER BY DICTID;");

		
		print(conn, "多表变量长度大于8", VarKey.CASE_VAR_LENGTH_8 , new Dict(), "SELECT * FROM DICT WHERE LENGTH(BIANLIANG)>8 AND BIANLIANG NOT LIKE CONCAT('bz_',CRF) AND FLAG IS NOT NULL AND FLAG!='' ORDER BY DICTID;");

		
		print(conn, "单表变量长度大于10", VarKey.CASE_VAR_LENGTH_10, new Dict(), "SELECT * FROM DICT WHERE LENGTH(BIANLIANG)>10 AND BIANLIANG NOT LIKE CONCAT('bz_',CRF) AND (FLAG IS NULL OR FLAG='') ORDER BY DICTID;");

		
		ResultSet rs4 = initStmt.executeQuery("SELECT DICTID,BVALUE FROM DICT WHERE BVALUE IS NOT NULL AND BVALUE != '';");
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
			print(conn, "bvalue编写不规范", VarKey.CASE_B_VAR_RULE, new Dict(), "SELECT * FROM DICT WHERE DICTID IN ("+ bValueIds + ") ORDER BY DICTID;");
		}
		

		print(conn, "变量名命名有特殊符号", VarKey.CASE_VAR_SPEC_CODE, new Dict(), "SELECT * FROM DICT WHERE BIANLIANG NOT REGEXP '^[a-zA-Z0-9_]*$' ORDER BY DICTID;");
	
		
		print(conn, "flag标记有特殊符号", VarKey.CASE_Flag_SPEC_CODE, new Dict(), "SELECT * FROM DICT WHERE FLAG NOT REGEXP '^[a-zA-Z0-9_]*$' ORDER BY FLAG;");		
		
		
		ResultSet rs5 = initStmt.executeQuery("SELECT * FROM DICT;");
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
			print(conn, "变量字典中有英文的逗号和英文的双引号", VarKey.CASE_DICT_HAS_BD, new Dict(), "SELECT * FROM DICT WHERE DICTID IN ("+bdIds+");");		
		}
		
		
		if(WebConfig.checkFlag){
			ResultSet flagIds = initStmt.executeQuery("SELECT MIN(DICTID) FROM DICT WHERE FLAG NOT LIKE 'v%' AND FLAG != '' AND FLAG IS NOT NULL GROUP BY FLAG;");
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
				print(conn, "变量字典中访视标记不能形同V1，V2...", VarKey.CASE_FLAG_V1_V2,new Dict(), "SELECT * FROM DICT WHERE DICTID IN ("+flagIdStr+");");
			}
		}

		
		print(conn, "变量字典比表结构表多余的表",VarKey.CASE_DICT_MORE_STRUCTURE,new Dict(),"SELECT * FROM DICT WHERE CRF NOT IN(SELECT TABLENAME FROM STRUCTURE)GROUP BY CRF ORDER BY DICTID;");
		
		/**********Structrue**********/
		print(conn, "表名含有关键字", VarKey.CASE_TABLE_HAS_KEY, new Structrue(), "SELECT * FROM STRUCTURE WHERE TABLENAME IN (SELECT WORD FROM KEYWORD) ORDER BY ID");

		
		print(conn, "表结构表中的“表类型”与“标识”不一致", VarKey.CASE_TYPE_FLAG, new Structrue(), "SELECT * FROM STRUCTURE WHERE (TABLETYPE = 1 AND (TABLELX != '单表' OR (TABLEITEM IS NOT NULL AND TABLEITEM !=''))) OR (TABLETYPE = 2 AND(TABLELX !='多表'   OR TABLEITEM != 'flag')) OR (TABLETYPE = 3 AND(TABLELX !='多表' OR TABLEITEM NOT LIKE '%_num'));");

		
		print(conn, "表结构表中的“关键字”不符合“表名_id”的形式", VarKey.CASE_STRUCTURE_KEY, new Structrue(), "SELECT * FROM STRUCTURE WHERE TABLEKEY NOT LIKE CONCAT(TABLENAME,'_id');");
		
		
		print(conn, "表结构表中的“标识变量”不符合规范（flag、表名_num）", VarKey.CASE_STRUCTURE_ITEM, new Structrue(), "SELECT * FROM STRUCTURE WHERE (TABLETYPE = 3 AND TABLEITEM NOT LIKE CONCAT(TABLENAME,'_num')) OR (TABLETYPE = 2 AND TABLEITEM != 'flag');");
		
		
		print(conn, "表结构表的表名不是字母和数字的组合", VarKey.CASE_STRUCTURE_TABLE_NAME, new Structrue(), "SELECT * FROM STRUCTURE WHERE TABLENAME NOT REGEXP '^[a-zA-Z0-9]*$';");
		
		
		print(conn, "表结构表比变量字典多余的表",VarKey.CASE_STRUCTURE_MORE_DICT,new Structrue(),"SELECT * FROM STRUCTURE WHERE TABLENAME NOT IN(SELECT DISTINCT CRF FROM DICT) ORDER BY ID;");
		
		DBManager.closeConnection(conn);
		
		serverInterface.setAttribute("data", checkResult);
		serverInterface.setAttribute("dTag", dTagList);
		serverInterface.setAttribute("sTag", sTagList);
		serverInterface.setAttribute("bdTag", bdIdMap);
		serverInterface.setAttribute("count", questionCount);
		System.err.println("核查结束");
		
		return "/WEB-INF/report.jsp";
	}

	private <T> void print(Connection conn,String errorMsg, String tag, T t, String sql)
			throws Exception {
		CRUD crud = new CRUD();
		List<T> results = crud.getBeanFactory().queryBeans(conn, sql, t);
		System.err.println("errorMsg == > " + errorMsg);
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
