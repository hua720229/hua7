package com.lt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lt.pojo.Item;
import com.lt.pojo.ItemDesc;
import com.lt.service.ItemService;
import com.lt.vo.EasyUIData;
import com.lt.vo.SysResult;
/**
 * 后台增删改查的controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/query")
	public EasyUIData findById(Integer page, Integer rows) {
		return itemService.findById(page,rows);
	}
	@RequestMapping("/save")
	public SysResult itemSave(Item item,ItemDesc itemDesc) {
		try { //实现数据新增
			itemService.itemSave(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.file();
		}
	}
	@RequestMapping("/delete")
	public SysResult itemDelete(Long[] ids) {
		try {
			itemService.itemDelete(ids);
			return SysResult.ok();
		} catch (Exception e) {
		e.printStackTrace();
		return SysResult.file();
		}
	}
	@RequestMapping("/update")
	public SysResult itemUpdate(Item item,ItemDesc itemDesc) {
		try {
			itemService.itemUpdate(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.file("修改商品失败！");
		}
	}
	@RequestMapping("/instock")
	public SysResult itemInstock(Long[] ids) {
		try {
			int status = 2; //下架
			itemService.ItemUpdate(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.file();
		}
	}
	@RequestMapping("/reshelf")
	public SysResult itemReshelf(Long[] ids) {
		try {
			int status = 1;//上架
			itemService.ItemUpdate(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.file();
		}
	}
	//实现商品描述的回显
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult itemDescById(@PathVariable Long itemId) {
		try {
			ItemDesc itemDesc=itemService.itemDescById(itemId);
			return SysResult.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.file();
		}
	}
}
