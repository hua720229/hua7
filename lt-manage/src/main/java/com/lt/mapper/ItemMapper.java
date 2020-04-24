package com.lt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.pojo.Item;


public interface ItemMapper extends BaseMapper<Item>{
   @Select("select * from tb_item limit #{start},#{rows}")
   List<Item> findItemByPage(@Param("start")Integer start,@Param("rows")Integer rows);

 //  void deleteItem(Long[] ids);

   
}
