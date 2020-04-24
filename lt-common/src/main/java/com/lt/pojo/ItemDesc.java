package com.lt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo{
	@TableId //标识主键自增
private Long itemId;
private String itemDesc;
}
