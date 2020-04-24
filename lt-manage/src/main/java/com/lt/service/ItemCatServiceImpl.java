package com.lt.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lt.mapper.ItemCatMapper;
import com.lt.pojo.ItemCat;
import com.lt.vo.EasyUItree;
//获取叶子类目数据的名称(560收到数据让用户看到是手机)
@Service
public class ItemCatServiceImpl implements ItemCatService{
	//@Autowired
	//private Jedis jedis;
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public String findItemCatName(Long itemCatId) {
		ItemCat itemCat= itemCatMapper.selectById(itemCatId);
		return itemCat.getName();

	}
	/**
	 * 1.根据parentId查询数据库记录返回itemCatList集合
	 * 2.将itemCatList集合中的数据按照指定的格式封装为List<EasyUItrue>
	 */
	@Override
	public List<EasyUItree> findItemCatList(Long parentId) {
		List<ItemCat>listCat=findIteCatList(parentId);
		List<EasyUItree> arrayList = new ArrayList<>();
		for (ItemCat easyUItree : listCat) {
			//第二种方法EasyUItree uItree=new EasyUItree();
			//			uItree.setId(easyUItree.getId());
			//			uItree.setText(easyUItree.getName());
			Long id = easyUItree.getId();
			String text = easyUItree.getName();
			//如果是父级树机构则closed，不是则open
			String state=	easyUItree.getIsParent()?"closed":"open";
			EasyUItree easyUItree2 = new EasyUItree(id, text, state);
			//          uItree.setState(string);
			arrayList.add(easyUItree2);
		}
		return arrayList;
	}
	public List<ItemCat>findIteCatList(Long parentId){
		QueryWrapper<ItemCat>queryWrapper= new QueryWrapper<>();
		queryWrapper.eq("parent_id",parentId );
		return 	itemCatMapper.selectList(queryWrapper);

	}
	//已经利用AOP查询缓存了则不需要这么繁琐了
/*	@Override
	public List<EasyUItree> findItemCatCache(Long parentId) {
		String key = "ITEM_CAT_"+parentId;
		List<EasyUItree> listtree = new ArrayList<>(); 
		String result = jedis.get(key);
		//判断缓存是否有数据
		if (StringUtils.isEmpty(result)) {
			listtree = findItemCatList(parentId);
			String json = ObjectMapperUtil.json(listtree);
			jedis.setex(key, 7 * 24 *3600, json);
		}else {           
			//缓存有数据转化成KV结构数据id=1
			listtree=ObjectMapperUtil.object(result, listtree.getClass());
		}
		return listtree;
	}*/
}
