package com.check.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebConfig {
	
	//核查课题名称
	public static String PROJECTNAME = "连接远程";
	
	//是否是PDC课题:true为PDC，false为EDC
	public static boolean ISPDC = false;
	
	//是否检查：变量字典中访视标记不能形同V1，V2...true为检查flag标记，false为不检查
	public static boolean checkFlag = true;

/*======================================================================*/	
	//核查人签名
	public static String CHECKER = "张波";

	//连接数据库名称 
	public static String DBNAME = "check";
	
	public static String TITLE = "_变量字典核查报告";
	
	public static String YYYYMMDD="yyyy-MM-dd";
	
	public static String HHMMSS="HH:mm:ss";
	
	public static String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
	
	public static String UTF8="UTF-8";
	
	public static String GB2312="GB2312";

	public static List crfsSingle = new ArrayList();

	public static Map crfsMap = new HashMap();

	public static List crfsSub = new ArrayList();

	public static Map crfsSubMap = new HashMap();

	public static int crftotal = 9;
	
	public static String getFontSize(){
		String fontSize = "";
		if(PROJECTNAME.length() <= 26 && PROJECTNAME.length() > 0){
			fontSize = "xx-large";
		}else{
			fontSize = "x-large";
		}
		return fontSize;
	}

	public static String substringNum(String str, int length) {
		if (str.length() > length) {
			return str.substring(0, length) + "...";
		}

		return str;
	}
}
