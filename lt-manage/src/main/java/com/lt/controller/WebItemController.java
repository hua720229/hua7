package com.lt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.pojo.Item;
import com.lt.pojo.ItemDesc;
import com.lt.service.ItemService;

//后台接受用户请求获取商品信息
@RestController
@RequestMapping("/web/item")//接受web的请求
public class WebItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/findItemById") 
	public Item findItem(Long id) {
		return itemService.findById(id);
	}
	@RequestMapping("/findItemDescById")
	public ItemDesc findItemDescItem(Long id) {
		return itemService.itemDescById(id);
	}
}
