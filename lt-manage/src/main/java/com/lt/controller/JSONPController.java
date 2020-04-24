package com.lt.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
@RestController
public class JSONPController {
@RequestMapping("/web/testJson")
//返回值为JSONPObject以下方法
public JSONPObject testJson(String callback) {
	User user = new User();
	user.setId(100);
	user.setAge(18);
	user.setName("熊大");
//	String json = ObjectMapperUtil.json(user);返回值为String的方法只是换了API意思都一样
//	return callback+"("+json+")";
	JSONPObject object = new JSONPObject(callback, user);
	return object;
}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class User{
	private Integer id;
	private Integer age;
	private String name;
}
}
