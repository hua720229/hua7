package com.lt.service;

import com.lt.pojo.User;

//定义中立接口
public interface DubboUserService {

	void saveUser(User user);

	String findByUP(User user);

}
