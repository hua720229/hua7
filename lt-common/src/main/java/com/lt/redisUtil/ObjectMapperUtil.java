package com.lt.redisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

//把对象转化成json数据和把json转化成对象的工具类
public class ObjectMapperUtil {
private static final ObjectMapper MAPPER = new ObjectMapper();
public static String json(Object objectUtil) {
	String json=null;
	try {
		 json = MAPPER.writeValueAsString(objectUtil);
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
	}
	return json;
} 
public static <T> T object(String json,Class<T>object) {
	T t =null;
	try {
		MAPPER.readValue(json,object);
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException();
	}
	return t;
}
}
