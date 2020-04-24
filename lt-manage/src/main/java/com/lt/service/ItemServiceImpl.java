package com.lt.service;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lt.mapper.ItemDescMapper;
import com.lt.mapper.ItemMapper;
import com.lt.pojo.Item;
import com.lt.pojo.ItemDesc;
import com.lt.vo.EasyUIData;
@Service
public class ItemServiceImpl implements ItemService {
	//获取所有数据并进行分页
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;//实现商品描述入库
          //page起始位置0  rows查询到行数20
	@Override
	public EasyUIData findById(Integer page, Integer rows) {
		//获取商品总数量
		int total =itemMapper.selectCount(null);
		//回传分页之后的数据
		int start = (page-1)*rows;
		List<Item>items=itemMapper.findItemByPage(start,rows);
		return new EasyUIData(total,items);
	}
	//实现数据新增
	//利用Spring中的Aop来进行事务的控制
	@Transactional
	@Override
	public void itemSave(Item item,ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		//两张表同时入库
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(item.getCreated());       
		itemDesc.setUpdated(item.getCreated());       
		itemDescMapper.insert(itemDesc);
	}
	//实现数据的删除 

 	@Transactional
	@Override
	public void itemDelete(Long[] ids) { 
 		//手动删除
		//itemMapper.deleteItem(ids);
		//数组转换成集合自动删除不需要再配置文件写sql语句
		List<Long> itemList=Arrays.asList(ids);
		itemMapper.deleteBatchIds(itemList);
		//同时删除ItemDesc表的数据(同时删除两张表的数据)
		itemDescMapper.deleteBatchIds(itemList);
	}
	//实现数据的更新编辑操作
	@Transactional
	@Override
	public void itemUpdate(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		//更新ItemDesc表的数据(同时更新两张表的数据)
		itemDesc.setUpdated(item.getUpdated());
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
	}
	//实现商品的上架或者下架
	@Override
	public void ItemUpdate(Long[] ids,Integer status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		List<Long>longids=Arrays.asList(ids);
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
		updateWrapper.in("id", longids);
		itemMapper.update(item, updateWrapper);	
	}
	//实现商品图片描述回显
	@Override
	public ItemDesc itemDescById(Long itemId) {	
	return itemDescMapper.selectById(itemId);
	}
	//实现商品具体信息的回显
	@Override
	public Item findById(Long id) {
		return itemMapper.selectById(id);
	}
}
