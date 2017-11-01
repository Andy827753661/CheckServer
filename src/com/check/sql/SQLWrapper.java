package com.check.sql;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.check.config.WebConfig;


public class SQLWrapper {
	public static String sqlOf(String sql,Map<String, Object> map)throws Exception{
		if(map==null){
			System.out.println("Project:"+WebConfig.PROJECTNAME+" Invoke SQL -> \n"+sql);
			return sql;
		}
		else{
			String[] contents=sql.split("@");
			String result="";
			for(String str:contents){
				if(str.startsWith("{")){
					String fieldName=str.substring(str.indexOf("{")+1,str.indexOf("}"));
					Object object=map.get(fieldName);
					if(object==null){
						result+=str.replace("{"+fieldName+"}", "null");
					}
					else{
						Class clazz=object.getClass();
						if(object instanceof Number){
							result+=str.replace("{"+fieldName+"}",object.toString());
						}
						else if(clazz==String.class){
							result+=str.replace("{"+fieldName+"}", "'"+(object.toString())+"'");
						}
						else if(clazz==Date.class){
							result+=str.replace("{"+fieldName+"}", "'"+(new SimpleDateFormat(WebConfig.YYYYMMDD).format((Date)object))+"'");
						}
						else if(clazz==Time.class){
							result+=str.replace("{"+fieldName+"}", "'"+(new SimpleDateFormat(WebConfig.HHMMSS).format((Time)object))+"'");
						}
						else if(clazz==Timestamp.class){
							result+=str.replace("{"+fieldName+"}", "'"+(new SimpleDateFormat(WebConfig.YYYYMMDDHHMMSS).format((Timestamp)object))+"'");
						}
						else{
							throw new Exception("Project:"+WebConfig.PROJECTNAME+" 封装SQL语句失败：原始类型是"+object.getClass());
						}
					}
				}
				else{
					result+=str;
				}
			}
			System.out.println("Project:"+WebConfig.PROJECTNAME+" Invoke SQL -> \n"+result);
			return result;
		}
	}
}
