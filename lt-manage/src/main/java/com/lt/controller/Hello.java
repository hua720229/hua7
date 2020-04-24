package com.lt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
@RequestMapping("hello")
public String hello() {
	return "我是8093服务器";
}
}
