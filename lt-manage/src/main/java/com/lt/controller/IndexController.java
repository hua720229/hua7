package com.lt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	/*
	 * 首页回显
	 */
	@RequestMapping("/page/{model}")
public String  itemadd(@PathVariable String model) {
	return model;
  }
}
