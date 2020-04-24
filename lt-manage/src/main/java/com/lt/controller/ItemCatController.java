package com.lt.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lt.anno.Cache_Find;
import com.lt.enu.KEY_ENUM;
import com.lt.service.ItemCatService;
import com.lt.vo.EasyUItree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
    private ItemCatService itemCatService;
	//实现根据Id查询商品分类信息common.js
	/**
	 * 1.用户发起post请求携带了itemCatid=560
	 * springmvc底层都是servlet对象，两大法宝request和response
	 * @return
	 */
	@RequestMapping("/queryItemName")
	public String findItemCatName(Long itemCatId) {
		
		return itemCatService.findItemCatName(itemCatId);
	}
	//根据Id查询树状节点的值
	//@RequestParam(value="id",defaultValue = "0")
	@RequestMapping("/list")
	@Cache_Find(key = "ITEM_CAT",keyType = KEY_ENUM.AUTO)
	public  List<EasyUItree> findItemCatList(@RequestParam(value="id",defaultValue = "0")Long parentId) {
		//Long parentId=0L;//0是树的父级菜单
		return itemCatService.findItemCatList(parentId);
		//return itemCatService.findItemCatCache(parentId);
	}
}
