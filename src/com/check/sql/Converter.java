package com.check.sql;

import java.lang.reflect.Field;
import java.util.Map;

import com.google.common.collect.Maps;

public class Converter {
	//将参数转化为对应的类型
	public static <T> T convertObject(Class<T> parameterClass, Object value)throws Exception{
		if(value!=null){
			if(parameterClass==String.class){
				return (T)value.toString();
			}
			return (T)parameterClass.getMethod("valueOf", String.class).invoke(null,value.toString());
		}
		return null;
	}
	
	//将Bean对象转化为Map
	public static Map<String, Object> beanToMap(Object...objects)throws Exception{
		Map<String, Object> map=Maps.newHashMap();
		for(Object object:objects){
			Class<?> clz=object.getClass();
			String className=clz.getSimpleName();
			Field[] fields=clz.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				map.put((className+"_"+field.getName()).toUpperCase(), field.get(object));
			}
		}
		return map;
	}
	
	
}
