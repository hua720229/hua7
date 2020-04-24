package com.lt.service;

import com.lt.pojo.Item;
import com.lt.pojo.ItemDesc;
import com.lt.vo.EasyUIData;

public interface ItemService {

	EasyUIData findById(Integer page, Integer rows); //分页查询

	void itemSave(Item item,ItemDesc itemDesc);  //商品入库以及商品描述入库

	void itemUpdate(Item item, ItemDesc itemDesc);  //商品更新

	void itemDelete(Long[] ids);  //商品删除

	void ItemUpdate(Long[] ids,Integer status); //商品的状态是上架还是下架

	ItemDesc itemDescById(Long itemId);  //实现商品图片描述的回显

	Item findById(Long id); //商品具体信息的回显

}
