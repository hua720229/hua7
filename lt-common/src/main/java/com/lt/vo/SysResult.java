package com.lt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//系统业务逻辑的返回值对象
@Data
@Accessors(chain = true)//链式加载
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
private Integer status;//200表示成功，201表示失败
private String msg;//后台返回值数据提示消息
private Object data; //后台返回任意数据
public static SysResult ok() {
	return new SysResult(200,null,null);
}
public static SysResult ok(Object data) {
	return new SysResult(200,null,data);
}
public static SysResult ok(String msg,Object data) {
	return new SysResult(200,msg,data);
}
public static SysResult file() {
	return new SysResult(201,null,null);
}
public static SysResult file(String msg) {
	return new SysResult(201,msg,null);
}
}
