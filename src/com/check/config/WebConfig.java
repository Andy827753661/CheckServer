package com.check.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebConfig {
	
	//�˲��������
	public static String PROJECTNAME = "E201721_����ҽԺ_����������­�⶯����խ";
	
	//�Ƿ���PDC����
	public static boolean ISPDC = false;
	
	//�˲����
	public static String TESTNUM = "1";
	
	//�������ݿ�����
	public static String DBNAME = "check";
	
	//�˲���ǩ��
	public static String CHECKER = "�Ų�";

/*======================================================================*/	
	
	public static String TITLE = "�����ֵ�˲鱨��";
	
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

	public static String substringNum(String str, int length) {
		if (str.length() > length) {
			return str.substring(0, length) + "...";
		}

		return str;
	}
}
