package com.lt.service;

import java.util.List;

import com.lt.vo.EasyUItree;

public interface ItemCatService {
	String findItemCatName(Long itemCatId);

	List<EasyUItree> findItemCatList(Long parentId);

	//List<EasyUItree> findItemCatCache(Long parentId);
}
