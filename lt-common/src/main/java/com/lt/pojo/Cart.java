package com.lt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_cart")
public class Cart extends BasePojo{
	@TableId(type = IdType.AUTO)
private Long id;
private Long userId; //用户ID和商品ID表示着用户的信息
private Long itemId;
private String itemTitle; //保存商品的第一张图片信息
private String itemImage;
private Long itemPrice;
private Integer num;
}
